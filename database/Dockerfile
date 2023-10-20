FROM mysql:latest

MAINTAINER okjaeook98@gmail.com

COPY init.sql /docker-entrypoint-initdb.d

ENV MYSQL_ROOT_PASSWORD=root

VOLUME /var/lib/mysql

EXPOSE 3307