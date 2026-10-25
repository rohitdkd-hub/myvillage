My Village — Java-powered site

This folder contains the Palchha village website. The Java server handles contact form validation, the backend greeting, and the current year.

Run locally with Java

```bash
cd /home/rohit/Desktop/project_site.
javac backend/Ser.java
java -cp backend Ser
```

Open http://localhost:8080/. Run the commands from the project root so the server can find the site files. The separate copy under `/frontend/` is available at http://localhost:8080/frontend/index.html.

Upload the source to GitHub

Create an empty GitHub repository, then run these commands from this folder (replace the URL with your repository URL):

```bash
git remote add origin https://github.com/YOUR_USERNAME/myvillage.git
git push -u origin main
```

This uploads the project source. GitHub Pages can host the static pages, but it cannot run this Java server or the contact form endpoint; deploy the Java backend separately for those features.

Deploy to Netlify (drag & drop)

- Zip the contents of this folder and drop it on the Netlify Sites dashboard, or connect your GitHub repository to Netlify for automatic deploys.

Deploy to Vercel

- Run `vercel` in this folder (install `vercel` CLI first) and follow prompts.

Notes
- The two Chittorgarh Fort gallery photos are served from Wikimedia Commons and include photo credits and license links.
- The site has no JavaScript assets. Gallery links open images directly, and weather/map links go to the relevant external service.

If you want, I can push this repo to GitHub for you (you'll need to provide the repo URL or give me permission to use your account via `gh`/CLI). I can also deploy directly to Netlify/Vercel if you authenticate locally — tell me which hosting you prefer and I'll continue.
