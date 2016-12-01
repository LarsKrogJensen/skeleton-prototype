package se.lars.únit

import se.lars.util.Publisher
import se.lars.util.Subscriber
import spock.lang.Specification


class PublisherSpec extends Specification{

    def "publisher test"()
    {
        setup:
        def sub1 = Mock(Subscriber)
        def sub2 = Mock(Subscriber)
        def pub = new Publisher()

        when:
        pub.subscribers << sub1 << sub2
        pub.send(msg)

        then:
        1 * sub1.receive(msg)
        1 * sub2.receive(msg)

        where:
        msg = "hello"
    }
}
