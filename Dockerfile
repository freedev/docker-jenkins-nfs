FROM jenkins

USER root

RUN apt-get update && apt-get install -y sudo nfs-client && rm -rf /var/lib/apt/lists/*

RUN usermod -aG sudo jenkins

RUN echo "" >> /etc/sudoers
RUN echo "# " >> /etc/sudoers
RUN echo "jenkins ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers

USER jenkins

COPY nfs-mount.groovy /usr/share/jenkins/ref/init.groovy.d/nfs-mount.groovy


