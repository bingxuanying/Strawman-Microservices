# StrawMan-microservice

## Brief Introduction

This a web service for managing records and images sent from home use motion detection camera.

* Adopted Spring framework for server end, Reids for caching JWT, and PostgreSQL for data storage. 

* Deployed it with Docker and Google Cloud Load Balancer on Kubernetes Cluster.

* Utilized Spring-Security for user authentication, Zuul for API gateway, Eureka for service discovery.


## Commands

Use kubeConfig.yml file to create pods and deploy on GCP Kubernetes

```
gcloud apply -f kubeConfig.yml
```


## APIs

* "example.com/auth/**" -- authentication purpose, including register and login

* "example.com/admin/**" -- user data manage purpose

* "example.com/api/**" -- user data access purpose
