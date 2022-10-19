package edu.java.spring.view;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

public abstract class AbtractPdfView extends AbstractView {
    public AbtractPdfView() {
        setContentType("application/pdf");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return false;
    }
    protected Document newDocument(){
        return new Document(PageSize.A4);
    }
    protected PdfWriter newWriter(Document document, OutputStream os) throws DocumentException {
        return PdfWriter.getInstance(document,os);
    }
    protected void PrepareWriter(Map<String,Object> model, PdfWriter writer, HttpServletRequest request){
        writer.setViewerPreferences(getViewerPreferences());
    }
    protected int getViewerPreferences(){
        return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ByteArrayOutputStream byteOutput = createTemporaryOutputStream();
        Document document = newDocument();
        PdfWriter writer = newWriter(document,byteOutput);
        PrepareWriter(model,writer,request);
        buildPdfMetadata(model,document,request);
        document.open();
        buildPdfDocument(model,document,writer,request,response);
        document.close();
        writeToResponse(response,byteOutput);

    }

    protected abstract void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request);

    protected abstract void buildPdfDocument(Map<String,Object> model,Document document,PdfWriter writer,HttpServletRequest request, HttpServletResponse response);
}
