package com.mycompany.ethicaljavalogger.services;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GoogleDriveService {
    private static GoogleDriveService instance;
    private final Drive service;
    
    private final String mainFolderId;
    private final String imagesFolderId;
    private final String logsFolderId;

    public GoogleDriveService() throws IOException, GeneralSecurityException {
        String applicationName = "EthicalJavaLogger";
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        java.io.File dataStoreDir = new java.io.File(System.getProperty("user.home"), "google-drive-quickstart");
        java.util.List<String> scopes = Collections.singletonList(DriveScopes.DRIVE);
        
        ConfigPropertiesService configPropertiesService = new ConfigPropertiesService();
        String clientId = configPropertiesService.getGoogleDriveClientId();
        String clientSecret = configPropertiesService.getGoogleDriveClientSecret();
        
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, clientId, clientSecret, scopes)
            .setDataStoreFactory(new FileDataStoreFactory(dataStoreDir))
            .setAccessType("offline")
            .build();

        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        
        this.service = new Drive.Builder(
            httpTransport, jsonFactory, credential)
            .setApplicationName(applicationName)
            .build();
        
        this.mainFolderId = this.createFolderIfNotExists("ethical_java_logger", null);
        this.imagesFolderId = this.createFolderIfNotExists("screen_captures", this.mainFolderId);
        this.logsFolderId = this.createFolderIfNotExists("logs", this.mainFolderId);
        System.out.println(this.mainFolderId);
        System.out.println(this.imagesFolderId);
        System.out.println(this.logsFolderId);
    }
    
    public static GoogleDriveService getInstance() throws IOException, GeneralSecurityException {
        if (instance == null) {
            instance = new GoogleDriveService();
        }
        
        return instance;
    }
    
    public Drive getService() {
        return this.service;
    }
    
    public String getMainFolderId() {
        return mainFolderId;
    }

    public String getImagesFolderId() {
        return imagesFolderId;
    }

    public String getLogsFolderId() {
        return logsFolderId;
    }
    
    private String createFolderIfNotExists(String folderName, String parentFolderId) {
        String folderId = this.getFolderIdByName(folderName, parentFolderId);
        
        try {
            if (folderId == null) {
                return this.createFolder(folderName, parentFolderId);
            }
        } catch (IOException ex) {
            Logger.getLogger(GoogleDriveService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return folderId;
    }
    
    private String getFolderIdByName(String folderName, String parentFolderId) {
        String pageToken = null;
        String query = "mimeType='application/vnd.google-apps.folder' and name='" + folderName + "'";

        if (parentFolderId != null) {
            query += " and '" + parentFolderId + "' in parents";
        }
        
        try {
            do {
                FileList result = this.getService().files().list()
                        .setQ(query)
                        .setSpaces("drive")
                        .setFields("nextPageToken, files(id)")
                        .setPageToken(pageToken)
                        .execute();

                for (File file : result.getFiles()) {
                    // Retorna o ID da primeira correspondência encontrada
                    return file.getId();
                }

                pageToken = result.getNextPageToken();
            } while (pageToken != null);
        } catch (IOException ex) {
            Logger.getLogger(GoogleDriveService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    private String createFolder(String folderName, String parentFolderId) throws IOException {
        File folderMetadata = new File();
        folderMetadata.setName(folderName);
        folderMetadata.setMimeType("application/vnd.google-apps.folder");
        
        if (parentFolderId != null) {
            folderMetadata.setParents(Collections.singletonList(parentFolderId));
        }

        File folder = this.getService().files().create(folderMetadata)
                .setFields("id")
                .execute();

        return folder.getId();
    }
    
    public void sendMedia(String pathImage, String folderId) {
        try {           
            java.io.File fileContent = new java.io.File(pathImage);
            File fileMetadata = new File();
            fileMetadata.setName("arquivo_drive_2.png");
            fileMetadata.setParents(Collections.singletonList(folderId));
            
            FileContent mediaContent = new FileContent("image/png", fileContent);
            
            File uploadedFile = this.getService().files().create(fileMetadata, mediaContent)
                    .setFields("id, parents")
                    .execute();
            
            System.out.println(uploadedFile.getId());
        }   catch (IOException ex) {
            Logger.getLogger(GoogleDriveService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}