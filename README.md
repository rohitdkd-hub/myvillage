# My Village — Palchha Village Website

A village website for Palchha in Chittorgarh, Rajasthan. The project includes a static frontend and a Java backend that handles form validation and server-side responses.

## Features

- Village information and overview
- Photo gallery with Chittorgarh Fort imagery
- Java backend for greeting and current-year logic
- Contact form validation and server response handling
- External weather and map links

## Project Structure

```text
project_site./
├── backend/
│   └── Ser.java
├── frontend/
│   ├── index.html
│   ├── styles.css
│   └── README.md
├── index.html
├── styles.css
├── palchha back.jpg
├── README.md
├── .gitignore
└── .nojekyll
```

The project root contains the main site files, while the frontend folder contains a separate static copy used for local testing or deployment.

## Run Locally

### Requirements

- Java Development Kit (JDK)
- Terminal or command-line interface

### Start the Java server

From the project root, compile and run the backend:

```bash
cd "/home/rohit/Desktop/project_site."
javac backend/Ser.java
java -cp backend Ser
```

Then open the app in a browser:

- Main site: http://localhost:8080/
- Frontend copy: http://localhost:8080/frontend/index.html

The Java backend must run from the project root so its relative file paths resolve correctly.

## Deployment

### Static hosting

The frontend can be hosted on services such as Netlify or Vercel.

- Netlify: upload the static site or connect the repository and set the publish directory appropriately.
- Vercel: run `vercel` in the project folder and follow the prompts.

Note: static hosting alone does not run the Java server. Backend features such as validation and server-side responses require Java hosting or another compatible server environment.

## GitHub

This repository is already connected to GitHub via the `origin` remote.

If you want to push new changes manually:

```bash
git add .
git commit -m "Update project documentation"
git push origin main
```

## Notes

- The gallery uses Wikimedia Commons image links.
- Weather and map links direct visitors to external services.
- The frontend can be deployed statically, while backend functionality requires a running Java server.

## Future Improvements

- Deploy the Java backend to a Java-compatible hosting service
- Connect the contact form to a real data source or email system
- Improve the frontend and backend integration for production deployment
