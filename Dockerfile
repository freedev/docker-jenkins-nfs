FROM jenkins

USER root

RUN apt-get update && apt-get install -y sudo nfs-client && rm -rf /var/lib/apt/lists/*

RUN usermod -aG sudo jenkins

RUN echo "" >> /etc/sudoers
RUN echo "# " >> /etc/sudoers
RUN echo "jenkins ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers

USER jenkins

COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/install-plugins.sh $(cat /usr/share/jenkins/plugins.txt)

COPY mount-nfs.sh /usr/local/bin/mount-nfs.sh

ENTRYPOINT ["/bin/tini", "--", "/usr/local/bin/mount-nfs.sh"]
