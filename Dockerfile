FROM ubuntu:latest
LABEL authors="John Rene"

ENTRYPOINT ["top", "-b"]