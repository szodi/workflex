# WorkFlex Homework

# Build as a docker image

Clone this repository

```bash
git clone https://github.com/szodi/workflex.git
```

Navigate to the `workflex` folder and build docker image

```bash
cd workflex
```

```bash
docker build --progress=plain --no-cache -t workflex:latest .
```

It takes some time. When the image is ready, run it

```bash
docker run -p 8080:8080 workflex:latest
```

# For developers

The API is generated with OpenAPI. If you want to build and run the application without Docker then **the first step has to be**

```bash
mvn clean install
```

It generates a `workflex-api.json` file under the `specs` folder in the **project root**
OpenAPI has a frontend task which runs when the dev server is started. `npm start` calls a `generate:api` task and it generates the client side DTO-s and services for Angular.
