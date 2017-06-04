package container.internal

import console.ConsoleWriter
import container.Canvas
import shapes.Rectangle
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by amitkumar on 3/6/17.
 */
class GraphicsContextSpec extends Specification {

    @Unroll
    def "if #point isValidForFill on canvas of dimension (20,4)"() {

        setup:
        ConsoleWriter consoleWriter = Mock(ConsoleWriter) {
            getOutStream() >> Mock(PrintStream) {
                println(_)
            }
        }
        Canvas canvas = Canvas.of(20, 4).withConsoleWriter(consoleWriter);
        GraphicsContext context = canvas.graphicsContext
        canvas.add(Rectangle.create(Point.of(14, 1), Point.of(18, 3)))

        expect:
        context.isValidForFill(point, color) == result

        where:
        point           | color       | result
        Point.of(10, 3) | 'c' as char | true
        Point.of(14, 1) | 'c' as char | false


    }

    @Unroll
    def "if #point isInsideCanvas canvas of dimension (20,4)"() {

        setup:
        Canvas canvas = Canvas.of(20, 4);
        GraphicsContext context = canvas.graphicsContext

        expect:
        context.isInsideCanvas(point) == result

        where:

        point           | result
        Point.of(10, 3) | true
        Point.of(21, 3) | false


    }
}
