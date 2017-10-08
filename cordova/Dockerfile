FROM criticalblue/nodejs

# == Installs latest version of PhoneGap

# Forces a create and build in order to preload libraries
RUN    npm install -g phonegap@latest && \
    npm install -g xmldom && \
    npm install -g xpath

RUN    cd /tmp && \
    phonegap create fakeapp && \
    cd /tmp/fakeapp && \
    phonegap build browser

RUN    cd && \
    rm -rf /tmp/fakeapp

VOLUME ["/data"]
WORKDIR /data

EXPOSE 3000

ENV PROJECT_HOME /appl/taximeter-cordova

RUN mkdir -p $PROJECT_HOME

WORKDIR $PROJECT_HOME

COPY . $PROJECT_HOME

WORKDIR $PROJECT_HOME

ENTRYPOINT phonegap serve
