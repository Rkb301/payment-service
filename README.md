# payment-service

A standalone Java 17 + Maven demo service with positive-amount validation and a fake local gateway. It listens on port `8083` and uses only JDK HTTP server APIs at runtime.

## Build and run

```powershell
mvn test
mvn -q -DskipTests package
java -cp target/classes com.example.payment.PaymentServiceApp
```

## Endpoints

- `POST /payments/charge` creates a valid sample charge and returns HTTP 200.
- `POST /payments/refund` accepts a sample refund and returns HTTP 202.
- `GET /payments/{id}` returns a sample payment.

Example: `curl -X POST http://localhost:8083/payments/charge`

`application-example.yml` records the local demo port and fake gateway. `repairbot.yaml` is a safe, non-secret AI repair configuration for use by the separate Python orchestrator; fake Jira/GitHub modes, dry-run behavior, and plan approval are enabled by default.
