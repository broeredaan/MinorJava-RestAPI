package com.ajp.yourgrade.model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

    public void print(String text) throws IOException {
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        yLoc -= 20;
        contentStream.newLineAtOffset(150, yLoc);
        contentStream.showText(text);
        contentStream.endText();
    }

    public void saveFile(int groupId) throws IOException {
        contentStream.close();
        document.save(groupId + "/" + groupId + ".pdf");
    }
}
