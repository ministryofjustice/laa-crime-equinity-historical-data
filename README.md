# laa-crime-equinity-historical-data

This is a Java based Spring Boot application hosted on [MOJ Cloud Platform](https://user-guide.cloud-platform.service.justice.gov.uk/documentation/concepts/about-the-cloud-platform.html).

[![MIT license](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![API docs](https://img.shields.io/badge/API_docs_-view-85EA2D.svg?logo=swagger)](https://laa-crime-means-assessment-dev.apps.live.cloud-platform.service.justice.gov.uk/open-api/swagger-ui/index.html)

### API endpoints

- /api/internal/v1/equinity/search/?
- /api/internal/v1/equinity/crm4/{usn}
- /api/internal/v1/equinity/crm5/{usn}
- /api/internal/v1/equinity/crm7/{usn}
- /api/internal/v1/equinity/crm14/{usn}
- /api/internal/v1/equinity/report/crm4/{from}/{to}
- /api/internal/v1/equinity/report/crm5/{from}/{to}
- /api/internal/v1/equinity/report/crm14/?

For more details, check file `data-api/open-api-specification.yml`  

## Application Set up

Clone Repository

```sh
git clone git@github.com:ministryofjustice/laa-crime-equinity-historical-data.git

cd laa-crime-equinity-historical-data
```

Make sure all tests are passed by running following ‘gradle’ Command

```sh
./gradlew clean test
```

You will need to build the artifacts for the source code, using `gradle`.

```sh
./gradlew clean build
```

```sh
docker-compose build
docker-compose up
```

laa-crime-equinity-historical-data application will be running on http://localhost:8089

[Crime Apps repository template guide](https://dsdmoj.atlassian.net/wiki/x/IADGAwE)

### Filling docker-compose.override.yml

In order to run docker image on local, a `docker-compose.override.yml` file is required. This file will contain sensible data and therefore is not available on git. However, a template is provided.
The first thing to do is to define the environment variables the application will expectect (look for $ on applicaiton.yaml file)

## Database

This project runs in a MS-SQL Server database. Currently, there is no option to run it locally.
Unit and Integration tests are running using mocked data over an H2 (memory) database with JPA driver to emulate MS-SQL one.

Application can run locally (java or docker) connecting to the RDS database via a tunnel.

### How to connect to a database tunnel

Connection to a database needs to be done from a tunnel. This tunnel is done from a pod that will serve a bridge between our local connection and the RDS on cloud platform.

[Crime Apps guide to local RDS connections](https://dsdmoj.atlassian.net/wiki/x/n4BLAAE)

See example below:


1. Create port-forward-pod
```
kubectl \
 -n laa-crime-equinity-historical-data-uat \
 run rds-port-forward-pod \
 --image=ministryofjustice/port-forward \
 --port=1433 \
 --env="REMOTE_HOST=cloud-platform-somethingherecheckcloudplatform.rds.amazonaws.com" \
 --env="LOCAL_PORT=1433" \
 --env="REMOTE_PORT=1433"
```

2. Open tunnel from local (on port 9433) to port-forward (on port 1433)
```
kubectl \
 -n laa-crime-equinity-historical-data-uat \
 port-forward \
 rds-port-forward-pod 9433:1433
```

3. This tunnel will redirect all connections to localhost:9433 to the port-forward:1433, and then the port forwards would redirect them to the RDS database server at port 1433

**Scripts** 

Create port-forward pod
```
kubectl \
 -n {namespace} \
 run {rds-pod-name} \
 --image=ministryofjustice/port-forward \
 --port={target-port} \
 --env="REMOTE_HOST=cloud-platform-34f4d161bec89a00.cdwm328dlye6.eu-west-2.rds.amazonaws.com" \
 --env="LOCAL_PORT={target-port}" \
 --env="REMOTE_PORT={target-port}"
```

The por-forward port only needs to be created once. And there is no need to create if it has been already created.
 
Open a tunnel to port-forward

```
kubectl \
 -n {namespace} \
 port-forward \
 rds-port-forward-pod {local-port}:{target-port}
```

Where: 

- {namespace} : use the valid namespace (e.g. laa-crime-equinity-historical-data-uat)
- {rds-pod-name} : give a name easy to remember and identify, and make sure it matches on both creation and opening (e.g. rds-port-forward-pod)
- {target-port} : this is teh port where usually the database is running (e.g. 1433 as it is the default for MSSQL Server)
- {local-port} : this is the port we want to have our local tunnel listening on (e.g. use same 1433, if changed to 9433 then use 9433 for localhost connections but it will redirect to {target-port} at the end of teh tunnel) 



## CI/CD

We have configured a GitHub Actions code pipelines. You can [log in](https://github.com/ministryofjustice/laa-crime-equinity-historical-data/actions) from here to access the pipeline.

To make any changes, create a branch and submit the PR. Once the PR is submitted the branch checks will kick off, and on success, it will wait for approval before merged.
Once PR is merged, image will be build and pushed automatically to AWS ECR. On success, the image will deploy automatically into UAT.
After successful deployment to UAT, deployment to higher environments (prod and archive) start automatically, but require manual approval.


### Open API

We have implemented the Open API standard (with Swagger 3). The web link provides a details Rest API with a schema definition. The link can only from local or from dev environment.
The swagger link can be found from [here](https://dsdmoj.atlassian.net/wiki/x/SQF0LAE)

## Application Monitoring and Logs

[//]: # ()
[//]: # ()
[//]: # ([Thanos]&#40;https://thanos.live.cloud-platform.service.justice.gov.uk&#41;)

[//]: # ()
[//]: # ([AlertManager]&#40;https://alertmanager.cloud-platform.service.justice.gov.uk&#41;)

[//]: # ()
[//]: # ([Grafana]&#40;https://grafana.cloud-platform.service.justice.gov.uk&#41;)

- Logs on [Kibana](https://kibana.cloud-platform.service.justice.gov.uk)
- Alerts on [Sentry](https://ministryofjustice.sentry.io/projects/laa-crime-equinity-historical-data/?project=4507690126540800)
- Metrics on [Prometheus](https://prometheus.cloud-platform.service.justice.gov.uk)

[Crime Apps guide on logging and metrics](https://dsdmoj.atlassian.net/wiki/x/ToHjBQE)

## Kubernetes 

Useful commands to access pods and kubernetes cluster information

### How to access the pods:

In order to access the pods, it is required to have Kubernetes installed and configured in your local machine. If you need help, check these documents:

- [Java Project Setup - Accessing Clusters](https://dsdmoj.atlassian.net/wiki/spaces/ASLST/pages/3761963077/Java+Project+Setup+with+CircleCI+and+Helm+on+Cloud+Platform#Accessing-the-clusters)
- [Connecting to the Cloud Platform's Kubernetes cluster - Cloud Platform User Guide](https://user-guide.cloud-platform.service.justice.gov.uk/documentation/getting-started/kubectl-config.html#installing-kubectl)

Assuming Kubernetes is all setup, follow these steps to access the pods.

1. Use the following command from terminal:

```sh
kubectl get pods -n {nameSpace}
```

Possible values for `nameSpace` are:

- laa-crime-equinity-historical-data-uat
- laa-crime-equinity-historical-data-prod
- laa-crime-equinity-historical-data-archive

Check response from command below, you will need that for the following step

```sh
kubectl get pods -n {nameSpace}
```

Output:

    NAME                                 READY   STATUS    RESTARTS   AGE
    {poddName}                           1/1     Running   0          18m

2. Access the pod console using the following command:

```sh
kubectl exec -it {podName} -n {nameSpace} -- sh
```

Example:

```sh
kubectl get pods -n laa-crime-equinity-historical-data-readme
```

Output should be similar to this:

    NAME                                             READY   STATUS    RESTARTS   AGE
    laa-crime-equinity-historical-data-00000-xxxxx   1/1     Running   0          18m

```shell
kubectl exec -it laa-crime-equinity-historical-data-00000-xxxxx -n laa-crime-equinity-historical-data-readme -- sh
```

That should give you access to the pods terminal.

### Additional guides

- [Cloud Platform user guide](https://user-guide.cloud-platform.service.justice.gov.uk/#application-logging)
- [Modernisation Platform Team Information](https://user-guide.modernisation-platform.service.justice.gov.uk/#modernisation-platform-team-information)


# 

[![repo standards badge](https://img.shields.io/endpoint?labelColor=231f20&color=005ea5&style=for-the-badge&label=MoJ%20Compliant&url=https%3A%2F%2Foperations-engineering-reports.cloud-platform.service.justice.gov.uk%2Fapi%2Fv1%2Fcompliant_public_repositories%2Fendpoint%2Ftemplate-repository&logo=data:image/png;base64,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)](https://operations-engineering-reports.cloud-platform.service.justice.gov.uk/public-github-repositories.html#template-repository)

### Ministry of Justice Compliant Repository

The current repository used this template to [create a repository] with the default initial files for a Ministry of Justice Github repository, including:

- The correct LICENSE
- Github Action example
- A .gitignore file
- A CODEOWNERS file
- A dependabot.yml file
- The MoJ Compliant Badge (Public repositories only)


[create a repository]: https://github.com/ministryofjustice/template-repository/generate
