package com.example.payment;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class PaymentServiceApp {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8083), 0);
        server.createContext("/payments", PaymentServiceApp::handlePayments);
        server.start();
        System.out.println("payment-service listening on 8083");
    }

    static void handlePayments(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        if ("POST".equals(exchange.getRequestMethod()) && "/payments/charge".equals(path)) {
            write(exchange, 200, charge(25));
            return;
        }
        if ("POST".equals(exchange.getRequestMethod()) && "/payments/refund".equals(path)) {
            write(exchange, 202, "{\"refund\":\"accepted\"}");
            return;
        }
        if ("GET".equals(exchange.getRequestMethod()) && path.startsWith("/payments/")) {
            write(exchange, 200, "{\"id\":\"pay-100\"}");
            return;
        }
        write(exchange, 404, "{\"error\":\"not found\"}");
    }

    public static String charge(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
        return "{\"paymentId\":\"pay-100\",\"amount\":" + amount + "}";
    }

    private static void write(HttpExchange exchange, int status, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream output = exchange.getResponseBody()) {
            output.write(bytes);
        }
    }
}
