package factory

import factory.CommandFactory
import factory.CommandType
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by amitkumar on 3/6/17.
 */
class CommandFactorySpec extends Specification {


    @Unroll
    def "return valid command type based on user input #input"(){

        expect:
        CommandFactory.create(input) == commandType

        where:
        input                                         | commandType
        "enter command: C 20  4 "                     | CommandType.CANVAS
        "enter command: C 2"                          | CommandType.UNKNOWN
        "enter command: L 1 2 6 2"                    | CommandType.LINE
        "enter command: L 1 2 6 2 4"                  | CommandType.UNKNOWN
        "enter command: R 14 1 18 3"                  | CommandType.RECTANGLE
        "enter command: B 10 3 o"                     | CommandType.BUCKET_FILL
        "enter command: B 10 3 4"                     | CommandType.BUCKET_FILL
        "enter command: B 10 o"                       | CommandType.UNKNOWN
        "enter command: B 10 3 12"                    | CommandType.UNKNOWN
        "enter command: Q"                            | CommandType.QUIT
        "C 20 4"                                      | CommandType.UNKNOWN
        "enter command:"                              | CommandType.UNKNOWN

    }

}
