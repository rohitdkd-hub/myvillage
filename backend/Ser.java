import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Ser {
    private static final Path ROOT = Path.of(System.getProperty("user.dir")).toAbsolutePath().normalize();
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", Ser::handleRequest);
        server.setExecutor(java.util.concurrent.Executors.newCachedThreadPool());
        server.start();
        System.out.println("Server running at http://localhost:8080/");
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();

        if (path.equals("/hello")) {
            send(exchange, 200, "text/plain; charset=utf-8", "Hello from Backend!");
            return;
        }
        if (path.equals("/contact")) {
            handleContact(exchange);
            return;
        }
        serveStaticFile(exchange, path);
    }

    private static void handleContact(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equals("POST")) {
            send(exchange, 405, "text/plain; charset=utf-8", "POST is required.");
            return;
        }
        String body = new String(exchange.getRequestBody().readNBytes(32_768), StandardCharsets.UTF_8);
        Map<String, String> form = parseForm(body);
        String name = form.getOrDefault("name", "").trim();
        String email = form.getOrDefault("email", "").trim();
        String message = form.getOrDefault("message", "").trim();
        boolean validEmail = Pattern.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", email);
        boolean valid = !name.isEmpty() && validEmail && !message.isEmpty();
        String result = valid
                ? "Thanks! Your message has been noted (demo)."
                : "Please provide a name, a valid email address, and a message.";
        String color = valid ? "#18723a" : "crimson";
        String html = "<!doctype html><html lang=\"en\"><meta charset=\"utf-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1\"><title>Contact</title>"
                + "<link rel=\"stylesheet\" href=\"/styles.css\"><main class=\"container card\"><h1>Contact</h1><p style=\"color:"
                + color + "\">" + escapeHtml(result) + "</p><a class=\"btn\" href=\"/\">Return to My Village</a></main></html>";
        send(exchange, valid ? 200 : 400, "text/html; charset=utf-8", html);
    }

    private static void serveStaticFile(HttpExchange exchange, String requestPath) throws IOException {
        if (!exchange.getRequestMethod().equals("GET") && !exchange.getRequestMethod().equals("HEAD")) {
            send(exchange, 405, "text/plain; charset=utf-8", "Method not allowed.");
            return;
        }
        String relative = requestPath.equals("/") ? "index.html" : requestPath.substring(1);
        Path file = ROOT.resolve(relative).normalize();
        if (!file.startsWith(ROOT) || !Files.isRegularFile(file)) {
            send(exchange, 404, "text/plain; charset=utf-8", "Not found.");
            return;
        }
        byte[] data = Files.readAllBytes(file);
        String type = Files.probeContentType(file);
        if (type == null) type = "application/octet-stream";
        if (type.startsWith("text/") || type.equals("application/javascript")) type += "; charset=utf-8";
        if (file.getFileName().toString().equals("index.html")) {
            String html = new String(data, StandardCharsets.UTF_8)
                    .replace("<span id=\"year\"></span>", String.valueOf(Year.now().getValue()))
                    .replace("@@BACKEND_MESSAGE@@", "Hello from Backend!");
            data = html.getBytes(StandardCharsets.UTF_8);
            type = "text/html; charset=utf-8";
        }
        exchange.getResponseHeaders().set("Content-Type", type);
        exchange.getResponseHeaders().set("X-Content-Type-Options", "nosniff");
        if (exchange.getRequestMethod().equals("HEAD")) {
            exchange.sendResponseHeaders(200, -1);
        } else {
            exchange.sendResponseHeaders(200, data.length);
            exchange.getResponseBody().write(data);
        }
        exchange.close();
    }

    private static Map<String, String> parseForm(String body) {
        Map<String, String> values = new HashMap<>();
        for (String field : body.split("&")) {
            String[] pair = field.split("=", 2);
            if (pair.length == 2) {
                values.put(URLDecoder.decode(pair[0], StandardCharsets.UTF_8),
                        URLDecoder.decode(pair[1], StandardCharsets.UTF_8));
            }
        }
        return values;
    }

    private static String escapeHtml(String value) {
        return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }

    private static void send(HttpExchange exchange, int status, String contentType, String body) throws IOException {
        byte[] data = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.getResponseHeaders().set("X-Content-Type-Options", "nosniff");
        exchange.sendResponseHeaders(status, data.length);
        exchange.getResponseBody().write(data);
        exchange.close();
    }
}
