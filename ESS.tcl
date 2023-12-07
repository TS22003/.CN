# Create a simple Energy-Saving Scheme (ESS) simulation in NS2

# Number of nodes
set numNodes 3

# Create a new simulation
set ns [new Simulator]

# Create nodes
for {set i 0} {$i < $numNodes} {incr i} {
    set node($i) [$ns node]
}

# Create a simple wireless channel
set channel [new Channel/WirelessChannel]
$channel set propagationDelay 10ms
$channel set delayModelType DelayModel

# Create a wireless interface
set intf0 [new Phy/WirelessPhy]
$intf0 set Channel $channel

# Create a wireless link
set link0 [new WirelessLink]
$link0 set Phy $intf0
$link0 set channel $channel
$link0 add-node $node(0)
$link0 add-node $node(1)

# Attach the wireless link to the nodes
$node(0) attach $intf0
$node(1) attach $intf0

# Set up energy model for nodes
set energyModel [new EnergyModel]
$energyModel set rxPower_ 0.2
$energyModel set txPower_ 0.5
$energyModel set idlePower_ 0.1

# Attach energy model to nodes
for {set i 0} {$i < $numNodes} {incr i} {
    $node($i) attach $energyModel
}

# Implement ESS (Energy-Saving Scheme): Simple sleep mode
proc setSleepMode {node} {
    $energyModel at 1.0 "$node setSleepMode true"
    $energyModel at 5.0 "$node setSleepMode false"
}

# Set up sleep mode for nodes
for {set i 0} {$i < $numNodes} {incr i} {
    setSleepMode $node($i)
}

# Create a TCP agent and attach it to the first node
set tcp [new Agent/TCP]
$tcp set class_ 2
$ns attach-agent $node(0) $tcp

# Create a sink agent and attach it to the second node
set sink [new Agent/TCPSink]
$ns attach-agent $node(1) $sink

# Connect the agents
$ns connect $tcp $sink

# Set up traffic
set ftp [new Application/FTP]
$ftp attach-agent $tcp
$ftp set type_ FTP

# Schedule the FTP traffic
$ns at 1.0 "$ftp start"
$ns at 9.0 "$ftp stop"

# Define the simulation end time
set simDuration 10.0

# Schedule the end of the simulation
$ns at $simDuration "finish"

# Run the simulation
$ns run