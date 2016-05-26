package view;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;

public class PdfStamperExample {

  public static void main(String[] args) {
    try {
      PdfReader pdfReader = new PdfReader("SimpleImages.pdf");

      PdfStamper pdfStamper = new PdfStamper(pdfReader,
            new FileOutputStream("HelloWorld-Stamped.pdf"));

      Image image = Image.getInstance("template5.jpeg");

      //for(int i=1; i<= pdfReader.getNumberOfPages(); i++){

          PdfContentByte content = pdfStamper.getUnderContent(pdfReader.getNumberOfPages());

          image.setAbsolutePosition(0f, 0f);

          content.addImage(image);
     // }

      pdfStamper.close();

    } catch (IOException e) {
      e.printStackTrace();
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }
}

