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
  - name: EQ_OAUTH_CLIENT_ID
    valueFrom:
      secretKeyRef:
        name: equinity-historical-data-oauth-client-id
        key: equinity-historical-data-oauth-client-id
  - name: EQ_OAUTH_CLIENT_SECRET
    valueFrom:
      secretKeyRef:
        name: equinity-historical-data-oauth-client-secret
        key: equinity-historical-data-oauth-client-secret
  - name: SENTRY_DSN
    value: {{ .Values.sentry_dsn }}
  - name: DB_DEBUG
    value: {{ .Values.database.debug | quote }}
  - name: DB_NAME
    value: {{ .Values.database.name }}
  - name: HOST_ENV
    value: {{ .Values.service.environment }}
{{- end -}}
