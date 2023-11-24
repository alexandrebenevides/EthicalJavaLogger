package com.mycompany.ethicaljavalogger.services;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
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
    private Drive service;

    public GoogleDriveService(String clientId, String clientSecret) throws IOException, GeneralSecurityException {
        String applicationName = "EthicalJavaLogger";
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        java.io.File dataStoreDir = new java.io.File(System.getProperty("user.home"), "google-drive-quickstart");
        java.util.List<String> scopes = Collections.singletonList(DriveScopes.DRIVE);
        
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
    }
    
    public String createFolderIfNotExists(String folderName) {
        String folderId = this.getFolderIdByName(folderName);
        
        try {
            if (folderId == null) {
                return this.createFolder(folderName);
            }
        } catch (IOException ex) {
            Logger.getLogger(GoogleDriveService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return folderId;
    }
    
    public String getFolderIdByName(String folderName) {
        String pageToken = null;

        try {
            do {
                FileList result = this.service.files().list()
                        .setQ("mimeType='application/vnd.google-apps.folder' and name='" + folderName + "'")
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
    
    private String createFolder(String folderName) throws IOException {
        File folderMetadata = new File();
        folderMetadata.setName(folderName);
        folderMetadata.setMimeType("application/vnd.google-apps.folder");

        File folder = this.service.files().create(folderMetadata)
                .setFields("id")
                .execute();

        return folder.getId();
    }
}