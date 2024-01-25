{{/*
Environment variables for service containers
*/}}
{{- define "laa-crime-equinity-historical-data.env-vars" }}
env:
  - name: DB_ENDPOINT
    valueFrom:
      secretKeyRef:
        name: rds-mssql-instance-output
        key: rds_instance_endpoint
  - name: DB_USER
    valueFrom:
      secretKeyRef:
        name: rds-mssql-instance-output
        key: database_username
  - name: DB_PASSWORD
    valueFrom:
      secretKeyRef:
        name: rds-mssql-instance-output
        key: database_password
  - name: DB_DEBUG
    value: {{ .Values.service.dbDebug | quote }}
  - name: HOST_ENV
    value: {{ .Values.service.environment }}
{{- end -}}
