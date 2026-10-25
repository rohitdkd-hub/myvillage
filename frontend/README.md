# My Village — Frontend

This folder contains the static frontend for the Palchha village website. The Java backend in the parent project folder handles server-side validation and response generation.

## Run locally

From the project root, start the Java backend:

```bash
cd "/home/rohit/Desktop/project_site."
javac backend/Ser.java
java -cp backend Ser
```

Then open the page in a browser:

- http://localhost:8080/frontend/index.html

## Notes

- This is a static frontend and does not require a build step.
- Gallery links open directly to image resources.
- Weather and map links point to external services.
- For full dynamic behavior, the Java backend must be running.
