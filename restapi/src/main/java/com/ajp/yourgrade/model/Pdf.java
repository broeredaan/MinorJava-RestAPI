package com.ajp.yourgrade.model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.ArrayList;

public class Pdf {
    private ArrayList<GroupMember> members;
    private int yLoc = 750;
    private PDDocument document;
    private PDPage page;
    private PDPageContentStream contentStream;

    public Pdf() throws IOException {
        document = new PDDocument();
        page = new PDPage();
        document.addPage(page);
        contentStream = new PDPageContentStream(document, page);
    }

    public void addPage() throws IOException {
        yLoc = 750;
        contentStream.close();
        PDPage newPage = new PDPage();
        document.addPage(newPage);
        contentStream = new PDPageContentStream(document,newPage);
    }

    public void print(String text,int fontSize) throws IOException {
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, fontSize);
        yLoc -= 20;
        contentStream.newLineAtOffset(50, yLoc);
        contentStream.showText(text);
        contentStream.endText();
    }

    public void saveFile(int groupId) throws IOException {
        contentStream.close();
        document.save( "download/" + groupId + ".pdf");
    }
}
