package com.project.securitybackend.service.definition;

import com.project.securitybackend.dto.request.CertificateRequestRequest;
import com.project.securitybackend.dto.request.EmailRequestDTO;
import com.project.securitybackend.dto.request.IssuerEndDateRequest;
import com.project.securitybackend.dto.response.CertificateRequestResponse;
import com.project.securitybackend.dto.response.IssuerEndDateResponse;
import com.project.securitybackend.dto.response.PossibleExtensionsResponse;

import java.util.List;
import java.util.UUID;

public interface ICertificateRequestService {

    CertificateRequestResponse createCertificateRequest(CertificateRequestRequest request) throws Exception;

    CertificateRequestResponse getCertificateRequest(UUID id) throws Exception;

    List<CertificateRequestResponse> getAllCertificateRequests() throws Exception;

    void approveCertificateRequest(CertificateRequestRequest request) throws Exception;

    void denyCertificateRequest(UUID certificateId) throws Exception;

    IssuerEndDateResponse getIssuerCertificateEndDate(IssuerEndDateRequest request);

    PossibleExtensionsResponse getPossibleExtensions(EmailRequestDTO request);
}
