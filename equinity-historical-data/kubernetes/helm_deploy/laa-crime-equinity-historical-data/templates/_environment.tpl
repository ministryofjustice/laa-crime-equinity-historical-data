{{/*
Environment variables for service containers
*/}}
{{- define "laa-crime-equinity-historical-data.env-vars" }}
env:
    - name: DB_ENDPOINT
        valueFrom:
        secretKeyRef:
        name: rds_instance_endpoint
        key: rds_instance_endpoint
    - name: DB_USER
        valueFrom:
        secretKeyRef:
        name: database_username
        key: database_username
    - name: DB_PASSWORD
        valueFrom:
        secretKeyRef:
        name: database_password
        key: database_password
  - name: HOST_ENV
    value: {{ .Values.service.environment }}
{{- end -}}
