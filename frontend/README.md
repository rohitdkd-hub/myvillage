My Village — Java-powered site

This folder contains a copy of the Palchha village website. Browser JavaScript has been removed. The Java server in the parent folder handles shop details, contact form validation, the backend greeting, and the current year.

Run locally with Java (from the parent project folder)

```bash
cd /home/rohit/Desktop/project_site.
javac backend/Ser.java
java -cp backend Ser
```

Open http://localhost:8080/frontend/index.html. Run the server from the project root so it can find the site files.

Host on GitHub Pages

1. Create a new repository on GitHub (do **not** initialize with a README).
2. In this folder run:

```bash
cd "/home/rohit/Desktop/c language/project "
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git
git push -u origin main
```

Then enable Pages in the repository settings (branch `main`, root `/`).

Deploy to Netlify (drag & drop)

- Zip the contents of this folder and drop it on the Netlify Sites dashboard, or connect your GitHub repository to Netlify for automatic deploys.

Deploy to Vercel

- Run `vercel` in this folder (install `vercel` CLI first) and follow prompts.

Notes
- Put images in this folder's `images/` subfolder.
- The site has no JavaScript assets. Gallery links open images directly, and weather/map links go to the relevant external service.

If you want, I can push this repo to GitHub for you (you'll need to provide the repo URL or give me permission to use your account via `gh`/CLI). I can also deploy directly to Netlify/Vercel if you authenticate locally — tell me which hosting you prefer and I'll continue.
