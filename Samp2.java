import java.lang.management.*;
import java.rmi.registry.*;
import java.util.*;
import javax.management.*;
import javax.management.remote.*;
import javax.management.remote.rmi.*;
import javax.rmi.ssl.*;
import javax.management.openmbean.CompositeData;


public class Samp2 {

	private JMXServiceURL target; 
	private JMXConnector connector;
	private MBeanServerConnection remote;
	

	private String metricProperty[] = { 		
												"kafka.server:type=ReplicaManager,name=PartitionCount",
												"kafka.controller:type=KafkaController,name=ActiveControllerCount",
												"kafka.controller:type=KafkaController,name=OfflinePartitionsCount",
												"kafka.controller:type=ControllerStats,name=LeaderElectionRateAndTimeMs",
												"kafka.server:type=BrokerTopicMetrics,name=BytesInPerSec",
												"kafka.server:type=BrokerTopicMetrics,name=BytesOutPerSec",
												"kafka.network:type=SocketServer,name=NetworkProcessorAvgIdlePercent",
												"kafka.server:type=BrokerTopicMetrics,name=TotalProduceRequestsPerSec",
												"kafka.server:type=BrokerTopicMetrics,name=TotalFetchRequestsPerSec",
												"kafka.controller:type=ControllerStats,name=UncleanLeaderElectionsPerSec",
												"kafka.server:type=ReplicaManager,name=UnderReplicatedPartitions",
												"kafka.server:type=ReplicaManager,name=LeaderCount",
												"kafka.server:type=KafkaRequestHandlerPool,name=RequestHandlerAvgIdlePercent",
												"kafka.network:type=SocketServer,name=NetworkProcessorAvgIdlePercent",
												"kafka.network:type=RequestChannel,name=RequestQueueSize",
												"kafka.server:type=BrokerTopicMetrics,name=MessagesInPerSec",
												"kafka.log:type=LogFlushStats,name=LogFlushRateAndTimeMs",
												"kafka.server:type=BrokerTopicMetrics,name=FailedProduceRequestsPerSec",
												"kafka.server:type=ReplicaFetcherManager,name=MaxLag,clientId=Replica",
												"kafka.server:type=BrokerTopicMetrics,name=FailedFetchRequestsPerSec"
											};
	private String metricName[]  = {		
											"<<<<<<<<<<<<<<<---@1..Number of partitions on this broker--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
											"<<<<<<<<<<<<<<<---@2..Number of active controllers in cluster--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
											"<<<<<<<<<<<<<<<---@3..Number of offline partitions--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
											"<<<<<<<<<<<<<<<---@4..Leader election rate and latency--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
											"<<<<<<<<<<<<<<<---@5..Aggregate Incoming Rate--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
											"<<<<<<<<<<<<<<<---@6..Aggregate Outgoing Rate--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
											"<<<<<<<<<<<<<<<---@7..Average fraction of time the network processor threads are idle--->>>>>",
											"<<<<<<<<<<<<<<<---@8..Produce request rate--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
											"<<<<<<<<<<<<<<<---@9..Fetch request rate--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
											"<<<<<<<<<<<<<<<---@10..Unclean leader election rate--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
											"<<<<<<<<<<<<<<<---@11..Number of unreplicated partitions--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
											"<<<<<<<<<<<<<<<---@12..Number of leaders on this broker--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
											"<<<<<<<<<<<<<<<---@13..Average fraction of time the request handler threads are idle--->>>>>>",
											"<<<<<<<<<<<<<<<---@14..Average fraction of time the network processor threads are idle--->>>>",
											"<<<<<<<<<<<<<<<---@15..Size of the request queue--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
											"<<<<<<<<<<<<<<<---@16..Aggregate incoming message rate.--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
											"<<<<<<<<<<<<<<<---@17..Log flush rate and time--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
											"<<<<<<<<<<<<<<<---@18..Produce request rate for requests that failed--->>>>>>>>>>>>>>>>>>>>>>",
											"<<<<<<<<<<<<<<<---@20..Maximum lag in messages between the follower and leader replicas-->>>>",
											"<<<<<<<<<<<<<<<---@19..Fetch request rate for requests that failed--->>>>>>>>>>>>>>>>>>>>>>>>"

										  };

	public Samp2() {
		try {
			target = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:9999/jmxrmi");
			connector = JMXConnectorFactory.connect(target);
			remote = connector.getMBeanServerConnection();
			connector.connect();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void getMetric(String attributeName) throws Exception {
		try {
			 ObjectName bean = new ObjectName(attributeName);
             MBeanInfo info = remote.getMBeanInfo(bean);
             MBeanAttributeInfo[] attributes = info.getAttributes();
             for (MBeanAttributeInfo attr : attributes) 
			    System.out.println(attr.getDescription() + " " + remote.getAttribute(bean,attr.getName()));
		 }
		 catch(Exception e) {
		 	System.out.println("Exception in " + attributeName + "\n" + e.getMessage());
		 }
	}

    public static void main(String[] args) throws Exception {
    	Samp2 s = new Samp2();
    	System.out.println("\nMetrics in KAFKA BROKER are:--\n");
        for(int i = 0;i < s.metricProperty.length;i++) {
        	System.out.println(s.metricName[i]);
        	s.getMetric(s.metricProperty[i]);
        	System.out.println("\n\n###############################################################################################\n\n");
        }
    }
}