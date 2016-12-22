import hudson.model.*;
import jenkins.model.*;

def sout = new StringBuilder(), serr = new StringBuilder()
def proc = 'sudo mount -t nfs -o nolock,nfsvers=3,hard fs-b933ca70.efs.eu-west-1.amazonaws.com:/ /var/efs/jenkins_home'.execute()
proc.consumeProcessOutput(sout, serr)
proc.waitForOrKill(1000)
println "out> $sout err> $serr"

