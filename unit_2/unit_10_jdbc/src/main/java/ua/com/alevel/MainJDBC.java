package ua.com.alevel;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import ua.com.alevel.entity.Location;
import ua.com.alevel.entity.Problem;
import ua.com.alevel.entity.Solution;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainJDBC {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/nix-4";
        String username = "root";
        String password = "hello";

        // Create graph
        Graph<String, DefaultWeightedEdge> graph =
                new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            List<Location> locations = readAllLocations(connection, graph);
            readAllRoutes(connection, graph);

            System.out.println("\n" + graph);
            System.out.println("\nFrom what location and where do you want to get?");

            String from = "From";
            String to = "To";

            from = getInput(locations, from);
            to = getInput(locations, to);

            Problem problem = new Problem();

            String finalFrom = from;
            problem.setFrom_id(locations.stream()
                    .filter(e -> e.getName().equals(finalFrom))
                    .findFirst()
                    .get().getId());

            String finalTo = to;
            problem.setTo_id(locations.stream()
                    .filter(e -> e.getName().equals(finalTo))
                    .findFirst()
                    .get().getId());

            addProblem(connection, locations, problem);
            List<Problem> problems = readAllProblems(connection);

            // Set id to current problem
            problem.setId(problems.stream()
                    .filter(e -> e.getFrom_id() == problem.getFrom_id() &&
                            e.getTo_id() == problem.getTo_id())
                    .findFirst().get().getId());

            Solution solution = new Solution();
            solution.setProblem_id(problem.getId());
            List<Solution> solutions = readAllSolutions(connection);

            solveProblem(connection, graph, solutions, solution, problem, from, to);
            solveProblems(connection, graph, locations, problems, solutions);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getInput(List<Location> locations, String destination) {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        System.out.print(destination + ": ");

        while (!isValid) {
            destination = scanner.nextLine();

            for (Location l : locations) {
                if (l.getName().equals(destination)) {
                    isValid = true;
                    break;
                }
            }

            if (!isValid) {
                System.out.print("Invalid input, try again: ");
            }
        }
        return destination;
    }

    private static void solveProblem(Connection connection, Graph<String, DefaultWeightedEdge> graph,
                                     List<Solution> solutions, Solution solution, Problem problem,
                                     String from, String to) throws SQLException {
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(graph);
        double cost;
        // Solution is unknown
        if (solutions.stream()
                .anyMatch(e -> e.getProblem_id() == solution.getProblem_id())
                && solutions.stream()
                .filter(e -> e.getProblem_id() == solution.getProblem_id())
                .findFirst().get().getCost() != 0) {
            cost = solutions.stream()
                    .filter(e -> e.getProblem_id() == problem.getId())
                    .findFirst()
                    .get().getCost();
            // Solution is known
        } else {
            cost = dijkstra.getPathWeight(from, to);
            solution.setCost((int) cost);
            solutions.add(solution);
            addSolution(connection, solution);
        }

        System.out.println("\nPath from " + from + " to " + to + " is " + (int) cost + " km long");

        GraphPath<String, DefaultWeightedEdge> path = dijkstra.getPath(from, to);
        List<String> pathString = path.getVertexList();

        // Print path
        for (int i = 0; i < pathString.size(); i++) {
            if (i == pathString.size() - 1) {
                System.out.print(pathString.get(i));
            } else {
                System.out.print(pathString.get(i) + " -> ");
            }
        }
        System.out.println();
    }

    private static void solveProblems(Connection connection, Graph<String, DefaultWeightedEdge> graph,
                                      List<Location> locations, List<Problem> problems,
                                      List<Solution> solutions) throws SQLException {
        double cost;
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(graph);

        for (Problem p : problems) {
            boolean isInDB = false;
            for (Solution s : solutions) {
                // Solution in db, but cost is not found
                if (p.getId() == s.getProblem_id() && s.getCost() == 0) {
                    cost = dijkstra.getPathWeight(locations.get(p.getFrom_id() - 1).getName(),
                            locations.get(p.getTo_id() - 1).getName());
                    s.setCost((int) cost);

                    addSolution(connection, s);
                    isInDB = true;
                    break;
                }
            }

            // Solution is not in db
            if (!isInDB) {
                Solution solution1 = new Solution();
                solution1.setProblem_id(p.getId());
                cost = dijkstra.getPathWeight(locations.get(p.getFrom_id() - 1).getName(),
                        locations.get(p.getTo_id() - 1).getName());
                solution1.setCost((int) cost);
                solutions.add(solution1);

                addSolution(connection, solution1);
            }
        }
    }

    private static List<Location> readAllLocations(Connection connection, Graph<String, DefaultWeightedEdge> graph)
            throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from locations " +
                "order by id";

        List<Location> locations = new ArrayList<>();
        // Read all locations
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            // Initialize graph vertices
            while (resultSet.next()) {
                Location location = new Location();

                location.setId(resultSet.getInt("id"));
                location.setName(resultSet.getString("name"));
                graph.addVertex(resultSet.getString("name"));

                locations.add(location);
            }
        }
        return locations;
    }

    private static void readAllRoutes(Connection connection, Graph<String, DefaultWeightedEdge> graph)
            throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select r.id, l1.name 'from', l2.name 'to', r.cost " +
                "from routes r " +
                "join locations l1 on r.from_id = l1.id " +
                "join locations l2 on r.to_id = l2.id";

        // Read all routes
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            // Initialize graph edges
            while (resultSet.next()) {
                String v1 = resultSet.getString("from");
                String v2 = resultSet.getString("to");
                int cost = resultSet.getInt("cost");

                DefaultWeightedEdge edge = graph.addEdge(v1, v2);
                if (edge != null) {
                    graph.setEdgeWeight(edge, cost);
                }
            }
        }
    }

    private static List<Problem> readAllProblems(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from problems";

        List<Problem> problems = new ArrayList<>();
        // Read all problems
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Problem problem = new Problem();

                problem.setId(resultSet.getInt("id"));
                problem.setFrom_id(resultSet.getInt("from_id"));
                problem.setTo_id(resultSet.getInt("to_id"));

                problems.add(problem);
            }
        }
        return problems;
    }

    private static void addProblem(Connection connection, List<Location> locations, Problem problem)
            throws SQLException {
        // If already present
        if (readAllProblems(connection).stream()
                .anyMatch(e -> (e.getFrom_id() == problem.getFrom_id() &&
                        e.getTo_id() == problem.getTo_id()))) {
            return;
        }

        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into problems " +
                        "values (default, ?, ?)");

        preparedStatement.setInt(1, problem.getFrom_id());
        preparedStatement.setInt(2, problem.getTo_id());

        preparedStatement.executeUpdate();
    }

    private static List<Solution> readAllSolutions(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from solutions";

        List<Solution> solutions = new ArrayList<>();
        // Read all solutions
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Solution solution = new Solution();

                solution.setProblem_id(resultSet.getInt("problem_id"));
                solution.setCost(resultSet.getInt("cost"));

                solutions.add(solution);
            }
        }
        return solutions;
    }

    private static void addSolution(Connection connection, Solution solution) throws SQLException {
        // if solution present but cost is 0
        if (readAllSolutions(connection).stream()
                .anyMatch(e -> (e.getProblem_id() == solution.getProblem_id() &&
                        e.getCost() == 0))) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update solutions " +
                            "set cost = ? " +
                            "where problem_id = ?");

            preparedStatement.setInt(1, solution.getCost());
            preparedStatement.setInt(2, solution.getProblem_id());

            preparedStatement.executeUpdate();

            // if solution present
        } else if (readAllSolutions(connection).stream()
                .anyMatch(e -> (e.getProblem_id() == solution.getProblem_id() &&
                        e.getCost() != 0))) {
            return;
            // If not in db
        } else {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into solutions " +
                            "values (?, ?)");

            preparedStatement.setInt(1, solution.getProblem_id());
            preparedStatement.setInt(2, solution.getCost());

            preparedStatement.executeUpdate();
        }
    }
}
