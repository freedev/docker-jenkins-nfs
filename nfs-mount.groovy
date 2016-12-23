import hudson.model.*;
import jenkins.model.*;

def nfs_type = System.getenv('MOUNT_TYPE')
def nfs_volume = System.getenv('MOUNT_VOLUME')
def nfs_options = System.getenv('MOUNT_OPTIONS')

println "nfs_type $nfs_type"
println "nfs_volume $nfs_volume"
println "nfs_options $nfs_options"

def sout = new StringBuilder(), serr = new StringBuilder(), mount_line = new StringBuilder()

// def proc = 'sudo mount -t nfs4 -o nfsvers=4.1,rsize=1048576,wsize=1048576,hard,timeo=600,retrans=2 fs-d514ed1c.efs.eu-west-1.amazonaws.com:/jenkins/docker_home /var/jenkins_home'.execute()

def command = null

mount_line.append("sudo mount ")

if (nfs_type) {
   mount_line.append(" -t ")
   mount_line.append(nfs_type)
   if (nfs_options) {
      mount_line.append(" -o ")
      mount_line.append(nfs_options)
   }
   if (nfs_volume) {
      mount_line.append(" ")
      mount_line.append(nfs_volume)
      mount_line.append(" /var/jenkins_home")
      command = mount_line.toString()
   }
}

if (command) {
   print "executing... "
   println command
   process = command.execute()
   println "done."
   process.consumeProcessOutput(sout, serr)
   process.waitForOrKill(1000)
   println "out> $sout err> $serr"
}

