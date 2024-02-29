class Sub:
    def __init__(self, broker):
        self.broker = broker
    def subscribe(self, topic, callback):
        if topic in self.broker.subs:
            self.broker.subs[topic].append(callback)
        else:
            callbacks = self.broker.subs[topic] = []
            callbacks.append(callback)
            
class Pub:
    def __init__(self, broker):
        self.broker = broker
    def publish(self, topic, message):
        subs = self.broker.subs.get(topic)
        if subs is not None:
            for sub in subs:
                threadpool.disaptch(sub, message)
                #sub(message)

class Broker:
    def __init__(self):
        self.subs = {}
    def create_pub(self):
        return Pub(self)
    def create_sub(self):
        return Sub(self)

def producer(topic, pub):
    v = 1
    while True:
        pub.publish(topic, v)
        v+=1
def consumer(msg):
    print(msg)

def main():
    broker = Broker()
    pub = broker.create_pub()
    thread.Thread(producer, "foo", pub)
    sub = broker.create_sub()
    sub.subscribe("foo", consumer)

# // Message Broker
# // Implement a in-process message broker based on topics such that there will be publishers publishing 
# // messages on specific topic(s) and subscribers that subscribe from these topic(s) to get the published messages.