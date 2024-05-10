package com.getposted.controller;

import java.io.IOException;
import java.io.OutputStream;

import com.getposted.enums.DownloadUrlPatterns;
import com.getposted.enums.Stored;
import com.getposted.fileHandler.FileManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "downloader", urlPatterns = { "/download/*" })
public class Downloader extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // /getPosted/download/purchase/id
        if (!checkTheUrlValidity(req.getRequestURI())) {
            ServeErrorPage.giveError(req, resp);
            return;
        }

        String[] resourceAndId = getResourceAndId(req.getRequestURI());

        switch (resourceAndId[0]) {
            case DownloadUrlPatterns.AUTHORPROFILEPICTUREURLPATTERN:
                HandleAuthorProfilePictureRequest(resp,resourceAndId[1]);
                break;
            case DownloadUrlPatterns.USERPROFILEPICTUREURLPATTERN:
                HandleUserProfilePictureRequest(resp,resourceAndId[1]);
                break;
            case DownloadUrlPatterns.PUBLICATIONPDFURLPATTERN:
                HandlePublicationPdfRequest();
                break;
            case DownloadUrlPatterns.PUBLICATIONTHUMBNAILURLPATTERN:
                HandlePublicationThumbnailRequest(resp,resourceAndId[1]);
                break;
            case DownloadUrlPatterns.CAROUSELPICTUREURLPATTERN:
                HandleHomePageCarouselPictureRequest(resp, resourceAndId[1]);
                break;
            default:
                ServeErrorPage.giveError(req, resp);
                return;
        }
    }

    private boolean checkTheUrlValidity(String url) {
        boolean isValid = true;
        String[] splittedUrl = url.split("/");
        if (splittedUrl.length != 5 )
            isValid = false;
        return isValid;
    }

    private String[] getResourceAndId(String url) {
        String[] parts = new String[2];
        String[] splittedUrl = url.split("/");

        for (int i = 0; i < splittedUrl.length; i++) {
            if (i == 3)
                parts[0] = splittedUrl[i];
            if (i == 4)
                parts[1] = splittedUrl[i];
        }

        return parts;
    }

    private void HandlePublicationPdfRequest() {
    }

    private void HandlePublicationThumbnailRequest(HttpServletResponse response, String id) {
        String fileName = id+".png";
        String defaultFileName = "defaultPdfThumbnail.png";
        readToResponseFile(response, fileName, defaultFileName, Stored.PUBLICATIONTHUMBNAIL);
    }

    private void HandleAuthorProfilePictureRequest(HttpServletResponse response, String id) {
        String fileName = id+".png";
        String defaultFileName = "defaultAuthorProfilePicture.png";
        readToResponseFile(response, fileName, defaultFileName, Stored.AUTHORPROFILEPICTURE);
    }

    private void HandleUserProfilePictureRequest(HttpServletResponse response, String id) {
        String fileName = id+".png";
        String defaultFileName = "defaultUserProfilePicture.png";
        readToResponseFile(response, fileName, defaultFileName, Stored.USERPROFILEPICTURE);
    }

    private void HandleHomePageCarouselPictureRequest(HttpServletResponse response, String id) {
        String fileName = id + ".png";
        String defaultFileName = "defaultCarouselPicture.png";
        readToResponseFile(response, fileName, defaultFileName, Stored.HOMEPAGECAROUSEL);
    }

    private long readToResponseFile(HttpServletResponse response, String fileName, String defaultFileName, String storedType){
        OutputStream outputStream = null;
        long size = 0;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        try {
            size = FileManager.retriveFile(fileName, outputStream, storedType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        if (size <= 0){
            try {
                outputStream = response.getOutputStream();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
            try {
                size = FileManager.retriveFile(defaultFileName, outputStream, Stored.DEFAULT);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        if (size <= 0) {
            try {
                size = FileManager.retriveFileFromResource(defaultFileName, outputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        response.setContentType("image/png");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));

        return size;
    }

}
