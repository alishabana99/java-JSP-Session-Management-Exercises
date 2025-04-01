package org.flo99rida.myjsps;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/*********************** IMPORTANT NOTE ***************************
 * The default Servlet API throws exceptions before you can process
 * individual files.
 * We'll use the Apache Commons FileUpload library for finer control.
 ****************************************************************/

// FileUploadServlet.java
@WebServlet("/fileupload")
@MultipartConfig(
        //Files larger than this are written to a temp dir on disk during processing (not in RAM)
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class FileUploadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> successMessages = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();

        try {
            // Get uploaded file part
            Collection<Part> filesParts = request.getParts();

            // Create upload directory if it doesn't exist
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            for (Part part : filesParts) {
                if (!part.getName().equals("file") || part.getSize() <= 0)
                    continue;
                // Get file metadata
                String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String contentType = part.getContentType();
                long fileSize = part.getSize();

                // Sanitize filename and save
                String safeFileName = System.currentTimeMillis() + "_" + fileName.replaceAll("[^a-zA-Z0-9.-]", "_");
                String filePath = uploadPath + File.separator + safeFileName;

                try (InputStream fileContent = part.getInputStream()) {
                    Files.copy(fileContent, Paths.get(filePath));
                }

                successMessages.add(String.format(
                        "%s (%s - %d bytes)",
                        safeFileName, contentType, fileSize
                ));
            }


        } catch (Exception e) {
            errorMessages.add(String.format(
                    "Error uploading file: %s", e.getMessage()
            ));
        }

        request.setAttribute("success", successMessages);
        request.setAttribute("error", errorMessages);
        request.getRequestDispatcher("/fileupload.jsp").forward(request, response);
    }
}
