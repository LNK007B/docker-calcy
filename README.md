# Containerized Java Calculator (Docker, Maven, Tomcat)

A Java servlet calculator packaged into a Docker image with a multi-stage build.

![Calculator result](screenshot.png)

## Tech Stack
- Java 17, Servlet API, Maven
- Apache Tomcat 9
- Docker (multi-stage Dockerfile)

## How It Works
1. `index.html` shows a form and sends the numbers to `/calculate`.
2. `CalculatorServlet` (mapped with `@WebServlet("/calculate")`) reads the values, calculates, and returns the result as HTML.
3. Docker stage 1 compiles the app with Maven into `calculator.war`.
4. Docker stage 2 copies only the WAR into a Tomcat image, so Maven and the source code are not in the final image.

## Run It
```
docker build -t java-calculator .
docker run -d -p 8080:8080 --name calc java-calculator
```
Open http://localhost:8080

Stop and remove:
```
docker stop calc
docker rm calc
```

## Problems I Hit
- docker run failed with a pipe error because Docker Desktop’s engine wasn’t running. Fix: start Docker Desktop and wait for “Engine running.”
- Pulling an image failed with a TLS handshake timeout from Docker Hub. Fix: Retrying the command worked, so it was likely a temporary network issue.
- The package folder was named calcy while the code used calc. Fix: renamed the folder so the path matches the package line.

## What I Learned
- Difference between an image and a container
- Multi-stage builds and layer caching (copying pom.xml before src)
- Port mapping with -p
- Why Tomcat 9 is needed for javax.servlet code

## Next Steps
- Build and deploy this image automatically with a Jenkins pipeline
- Run it on an EC2 instance