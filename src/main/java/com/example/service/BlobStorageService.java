package com.example.service;

import com.azure.core.util.BinaryData;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.stereotype.Service;

@Service
public class BlobStorageService {

    private final BlobContainerClient containerClient;

    public BlobStorageService() {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .endpoint("https://ewalletappstorage.blob.core.windows.net")
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();

        this.containerClient = blobServiceClient
                .getBlobContainerClient("wallet-logs");
    }

    public void uploadLog(String fileName, String content) {
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        byte[] bytes = content.getBytes();
        blobClient.upload(BinaryData.fromBytes(bytes), true);
    }
}
