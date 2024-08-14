package gui

import model.ProcessingClass

import java.awt.datatransfer.StringSelection
import java.awt.{Toolkit, Color as AWTColor}
import javax.swing.BorderFactory
import scala.swing.{Color, *}
import scala.swing.event.ButtonClicked
import scala.util.Random


object Gui extends SimpleSwingApplication {

  def top: MainFrame = new MainFrame {
    title = "Sprint Name Generator"
    centerOnScreen()
    preferredSize = new Dimension(500, 300)

    val onColor: AWTColor = AWTColor.green
    val offColor: AWTColor = AWTColor.gray
    val buttonSize: Dimension = new Dimension(100, 50)

    val textLabel1 = new Label {
      text = "Word 1"
    }

    val textLabel2 = new Label {
      text = "Word 2"
    }

    val hiddenLabel = new Label {
      text = ""
    }

    val postfixLabel = new Label {
      text = ""
    }

    // Create the toggle button
    val toggleButton = new ToggleButton("Toggle Me")

    // Define actions for the toggle button
    listenTo(toggleButton)
    reactions += {
      case ButtonClicked(`toggleButton`) =>
        val status = if (toggleButton.selected) {
          toggleButton.text = "Toggle: On"
        } else toggleButton.text = "Toggle: Off"
    }

    val buttonItem1 = new Button("Item 1")
    val buttonItem2 = new Button("Item 2")
    val buttonQuality1 = new Button("Quality 1")
    val buttonQuality2 = new Button("Quality 2")

    val copyButton = new Button("Copy Name")

    val generateButton = new Button("Generate")
    val rotateButton = new Button("Reverse")
    val familyName = new Button("Of")
    val postfix = new Button("Postfix")

    val clearFamilyName = new Button("Clear Of")
    val clearPostfix = new Button("Clear Postfix")


    // Create a BoxPanel to hold the contents vertically
    val verticalPanel = new BoxPanel(Orientation.Vertical)

    verticalPanel.contents += new BoxPanel(Orientation.Horizontal) {
      contents += textLabel1
      contents += Swing.HStrut(10)
      contents += hiddenLabel
      contents += textLabel2
      contents += postfixLabel
      contents += Swing.HStrut(30)
      contents += copyButton
    }

    //    verticalPanel.contents += Swing.VStrut(10)
    //    verticalPanel.contents += toggleButton

    verticalPanel.contents += new BoxPanel(Orientation.Horizontal) {
      contents += new BoxPanel(Orientation.Vertical) {
        contents += buttonItem1
        contents += buttonQuality1
      }

      contents += new BoxPanel(Orientation.Vertical) {
        contents += buttonItem2
        contents += buttonQuality2
      }
    }


    verticalPanel.contents += new BoxPanel(Orientation.Horizontal) {
      contents += generateButton
      contents += Swing.HStrut(10)
      contents += rotateButton
      contents += Swing.HStrut(10)
      contents += familyName
      contents += Swing.HStrut(10)
      contents += postfix
    }

    verticalPanel.contents += new BoxPanel(Orientation.Horizontal) {
      contents += clearFamilyName
      contents += Swing.HStrut(10)
      contents += clearPostfix
    }

    verticalPanel.border = Swing.EmptyBorder(20, 20, 20, 20)

    // Set the contents of the main frame to the vertical panel
    contents = verticalPanel

    listenTo(buttonItem1)
    reactions += {
      case ButtonClicked(`buttonItem1`) =>
        val result = ProcessingClass.generate(true)
        updateTextLabel(textLabel1, result)
    }
    listenTo(buttonItem2)
    reactions += {
      case ButtonClicked(`buttonItem2`) =>
        val result = ProcessingClass.generate(true)
        updateTextLabel(textLabel2, result)
    }

    listenTo(buttonQuality1)
    reactions += {
      case ButtonClicked(`buttonQuality1`) =>
        val result = ProcessingClass.generate(false)
        updateTextLabel(textLabel1, result)
    }
    listenTo(buttonQuality2)
    reactions += {
      case ButtonClicked(`buttonQuality2`) =>
        val result = ProcessingClass.generate(false)
        updateTextLabel(textLabel2, result)
      //        Dialog.showMessage(contents.head, s"Result: $result", title = "Processing Result")
    }
    listenTo(generateButton)
    reactions += {
      case ButtonClicked(`generateButton`) =>

        val word1 = ProcessingClass.generate()
        val word2 = ProcessingClass.generate()
        if (ProcessingClass.getRandomNumber() % 2 == 0) {
          updateTextLabel(textLabel2, word1)
          updateTextLabel(textLabel1, word2)
        } else {
          updateTextLabel(textLabel1, word1)
          updateTextLabel(textLabel2, word2)
        }
    }
    listenTo(rotateButton)
    reactions += {
      case ButtonClicked(`rotateButton`) =>

        val text1 = textLabel1.text
        val text2 = textLabel2.text
        updateTextLabel(textLabel1, text2)
        updateTextLabel(textLabel2, text1)
    }

    listenTo(familyName)
    reactions += {
      case ButtonClicked(`familyName`) =>
        updateTextLabel(hiddenLabel, ProcessingClass.generatePrefix())
    }

    listenTo(postfix)
    reactions += {
      case ButtonClicked(`postfix`) =>
        updateTextLabel(postfixLabel, ProcessingClass.generatePostfix())
    }

    listenTo(clearFamilyName)
    reactions += {
      case ButtonClicked(`clearFamilyName`) =>
        updateTextLabel(hiddenLabel, "")
    }

    listenTo(clearPostfix)
    reactions += {
      case ButtonClicked(`clearPostfix`) =>
        updateTextLabel(postfixLabel, "")
    }

    listenTo(copyButton)
    reactions += {
      case ButtonClicked(`copyButton`) =>
        copyToClipboard(s"${textLabel1.text} ${textLabel2.text}")
    }
  }

  private def copyToClipboard(text: String): Unit = {
    val stringSelection = new StringSelection(text)
    val clpbrd = Toolkit.getDefaultToolkit.getSystemClipboard
    clpbrd.setContents(stringSelection, null)
  }

  def updateTextLabel(label: Label, text: String): Unit = {
    label.text = text
  }
}
