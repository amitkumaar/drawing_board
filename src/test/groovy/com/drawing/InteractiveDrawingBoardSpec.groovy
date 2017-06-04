package com.drawing

import com.drawing.InteractiveDrawingBoard
import console.ConsoleReader
import console.ConsoleWriter
import exceptions.OutsideBoundaryException
import spock.lang.Specification

/**
 * Created by amitkumar on 3/6/17.
 */
class InteractiveDrawingBoardSpec extends Specification {

    def "if canvas does not exist then throw IllegalStateException"() {
        setup:
        ConsoleReader reader = Mock(ConsoleReader)
        ConsoleWriter consoleWriter = Mock(ConsoleWriter)
        InteractiveDrawingBoard boardRunner = new InteractiveDrawingBoard(reader, consoleWriter)

        when:
        boardRunner.parseInputAndExecuteCommand("enter command: L 1 2 3 4")

        then:
        IllegalStateException ex = thrown()
        ex.message.contains("please create canvas to")
    }

    def "if input is invalid then throw IllegalArgumentException"() {
        setup:
        ConsoleReader reader = Mock(ConsoleReader)
        ConsoleWriter consoleWriter = Mock(ConsoleWriter)
        InteractiveDrawingBoard boardRunner = new InteractiveDrawingBoard(reader, consoleWriter)

        when:
        boardRunner.parseInputAndExecuteCommand("enter command: X 1 2 3 4")

        then:
        IllegalArgumentException ex = thrown()
        ex.message.contains("invalid input")
    }
    def "if container.Canvas draw command has -ve dimensation (12, -3) then throw IllegalArgumentException"() {
        setup:
        ConsoleReader reader = Mock(ConsoleReader)
        ConsoleWriter consoleWriter = Mock(ConsoleWriter)
        InteractiveDrawingBoard boardRunner = new InteractiveDrawingBoard(reader, consoleWriter)

        when:
        boardRunner.parseInputAndExecuteCommand("enter command: C 12 -3")

        then:
        IllegalArgumentException ex = thrown()
        ex.message.contains("dimension can not be negative")
    }

    def "if draw line command has coordinates (21,3) (10,1) outside canvas (20,4) then throw exceptions.OutsideBoundaryException exception"(){
        setup:
        ConsoleReader reader = Mock(ConsoleReader)
        ConsoleWriter consoleWriter = Mock(ConsoleWriter){
            getOutStream() >> Mock(PrintStream){
                println(_)
            }
        }
        InteractiveDrawingBoard boardRunner = new InteractiveDrawingBoard(reader, consoleWriter)
        boardRunner.parseInputAndExecuteCommand("enter command: C 20 4")

        when:
        boardRunner.parseInputAndExecuteCommand("enter command: L 21 3 10 1")

        then:
        OutsideBoundaryException ex = thrown()
        ex.message.contains("outside defined canvas boundary")

    }

    def "if draw line command has coordinates (10,3) (10,1) inside canvas (20,4) then draw the line and not throw exceptions.OutsideBoundaryException"(){
        setup:
        ConsoleReader reader = Mock(ConsoleReader)
        ConsoleWriter consoleWriter = Mock(ConsoleWriter){
            getOutStream() >> Mock(PrintStream){
                println(_)
            }
        }
        InteractiveDrawingBoard boardRunner = new InteractiveDrawingBoard(reader, consoleWriter)
        boardRunner.parseInputAndExecuteCommand("enter command: C 20 4")

        when:
        boardRunner.parseInputAndExecuteCommand("enter command: L 10 3 10 1")

        then:
        notThrown OutsideBoundaryException
    }

    def "if draw rectangle command has coordinates (14,1) (18,3) inside canvas(20*4) then draw the line and not throw exceptions.OutsideBoundaryException"(){
        setup:
        ConsoleReader reader = Mock(ConsoleReader)
        ConsoleWriter consoleWriter = Mock(ConsoleWriter){
            getOutStream() >> Mock(PrintStream){
                println(_)
            }
        }
        InteractiveDrawingBoard boardRunner = new InteractiveDrawingBoard(reader, consoleWriter)
        boardRunner.parseInputAndExecuteCommand("enter command: C 20 4")

        when:
        boardRunner.parseInputAndExecuteCommand("enter command: R 14 1 18 3")

        then:
        notThrown OutsideBoundaryException
    }
    def "if draw rectangle command has coordinates (14,1) (18,3) outside canvas(20*4) then draw the line and not throw exceptions.OutsideBoundaryException"(){
        setup:
        ConsoleReader reader = Mock(ConsoleReader)
        ConsoleWriter consoleWriter = Mock(ConsoleWriter){
            getOutStream() >> Mock(PrintStream){
                println(_)
            }
        }
        InteractiveDrawingBoard boardRunner = new InteractiveDrawingBoard(reader, consoleWriter)
        boardRunner.parseInputAndExecuteCommand("enter command: C 20 4")

        when:
        boardRunner.parseInputAndExecuteCommand("enter command: R 14 1 18 3")

        then:
        notThrown OutsideBoundaryException
    }

    def "if bucket fill command has coordinates inside canvas then color the canvas and not throw exceptions.OutsideBoundaryException"(){
        setup:
        ConsoleReader reader = Mock(ConsoleReader)
        ConsoleWriter consoleWriter = Mock(ConsoleWriter){
            getOutStream() >> Mock(PrintStream){
                println(_)
            }
        }
        InteractiveDrawingBoard boardRunner = new InteractiveDrawingBoard(reader, consoleWriter)
        boardRunner.parseInputAndExecuteCommand("enter command: C 20 4")

        when:
        boardRunner.parseInputAndExecuteCommand("enter command: B 10 3 c")

        then:
        notThrown OutsideBoundaryException
    }

    def "if bucket fill command has coordinates outside canvas then throw exceptions.OutsideBoundaryException"(){
        setup:
        ConsoleReader reader = Mock(ConsoleReader)
        ConsoleWriter consoleWriter = Mock(ConsoleWriter){
            getOutStream() >> Mock(PrintStream){
                println(_)
            }
        }
        InteractiveDrawingBoard boardRunner = new InteractiveDrawingBoard(reader, consoleWriter)
        boardRunner.parseInputAndExecuteCommand("enter command: C 20 4")

        when:
        boardRunner.parseInputAndExecuteCommand("enter command: B 20 5 c")

        then:
        thrown OutsideBoundaryException
    }

    def "execute multiple commands and then bucket fill"(){
        setup:
        ConsoleReader reader = Mock(ConsoleReader)
        ConsoleWriter consoleWriter = new ConsoleWriter()
        InteractiveDrawingBoard boardRunner = new InteractiveDrawingBoard(reader, consoleWriter)
        boardRunner.parseInputAndExecuteCommand("enter command: C 20 4")
        boardRunner.parseInputAndExecuteCommand("enter command: L 1 2 6 2")
        boardRunner.parseInputAndExecuteCommand("enter command: L 6 3 6 4")
        boardRunner.parseInputAndExecuteCommand("enter command: R 14 1 18 3")

        when:
        boardRunner.parseInputAndExecuteCommand("enter command: B 10 3 c")

        then:
        notThrown OutsideBoundaryException
    }
}
