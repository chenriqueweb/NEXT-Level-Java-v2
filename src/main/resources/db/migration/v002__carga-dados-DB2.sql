-- Carga de Empresas 
SELECT  '        Empresa empresa' ||
        TRIM(TO_CHAR(ROW_NUMBER() OVER (ORDER BY GCB.CD_EMPGCB ASC),'0000')) ||
        ' = new Empresa(' ||
        GCB.CD_EMPGCB || ', ' ||
        '"' || TRIM(GCB.NM_EMPGCB_GUR) || '", "' ||
        TRIM(SUBSTR(TO_CHAR(FNE.CD_EMPFNE_CGC_RAI, '00000000'),1,3)) || '.' ||
        TRIM(SUBSTR(TO_CHAR(FNE.CD_EMPFNE_CGC_RAI, '00000000'),4,3)) || '.' ||
        TRIM(SUBSTR(TO_CHAR(FNE.CD_EMPFNE_CGC_RAI, '00000000'),7,3)) || '/' ||       
        TRIM(TO_CHAR(FNE.CD_FNE_CGC_FIL, '0000')) || '-' ||
        TRIM(TO_CHAR(FNE.CD_FNE_CGC_DVR, '00')) || '", ' ||
        'formatterDate.parse("' || TO_CHAR(FNE.DT_FNE_ABE_EST, 'dd/mm/yyyy') || '"));' || CHR(13) ||  
        '        empresaService.addEmpresa(empresa' || TRIM(TO_CHAR(ROW_NUMBER() OVER (ORDER BY GCB.CD_EMPGCB ASC),'0000')) || ');'
        AS "Empresa"
 FROM jtrach.EMP_GCB GCB,
      jtrach.FNE FNE
 WHERE GCB.CD_EMPGCB_CGC_RAI = FNE.CD_EMPFNE_CGC_RAI
   AND GCB.CD_EMPGCB         = FNE.CD_EMPGCB
   AND GCB.CD_EMPGCB         > 0
   AND GCB.DT_EMPGCB_ENC  IS NULL
   AND (FNE.DT_FNE_ABE_EST IS NOT NULL OR
       FNE.DT_FNE_ABE_FED IS NOT NULL)
   AND FNE.CD_GRPFNE = 16               -- MARKETPLACE
 ORDER BY GCB.CD_EMPGCB       
  WITH UR;       


-- Carga de Unidades Federativas
SELECT
        '        Estado estado' ||  UPPER(EST.CD_EST_SIG) ||
        ' = new Estado(' ||
        '"' || UPPER(EST.CD_EST_SIG) || '", ' ||
        '"' || SUBSTR(TRIM(EST.NM_EST),1,1) || 
        SUBSTR(LOWER(TRIM(EST.NM_EST)),2) ||
        '");' || CHR(13) ||  
        '        estadoService.addEstado(estado' || UPPER(EST.CD_EST_SIG) || ');'
        AS "Estado"
  FROM JTRACH.EST EST
 ORDER BY EST.CD_EST_SIG  
  WITH UR;
  

-- Carga de Municipios
SELECT '        Municipio municipio' || MUN.CD_EST_SIG ||
       MUN.CD_MUN ||  
       ' = new Municipio(' || MUN.CD_MUN || ', "' ||
       SUBSTR(TRIM(MUN.NM_MUN),1,1) || 
       SUBSTR(TRIM(LOWER(MUN.NM_MUN)),2) || 
       '", estado' || MUN.CD_EST_SIG || ');' || CHR(13) ||  
       '        municipioService.addMunicipio(municipio' || UPPER(MUN.CD_EST_SIG) || 
       MUN.CD_MUN || ');'
       AS "Municipio"
  FROM JTRACH.MUN MUN
 WHERE MUN.CD_EST_SIG = 'ES'
 ORDER BY MUN.CD_MUN  
  WITH UR;
  

-- Carga de Filiais
SELECT '        FilialPK filialPK = new FilialPK();' || CHR(13) || CHR(13)
  FROM SYSIBM.SYSDUMMY1;

SELECT
       '        filialPK.setCodigoEmpresa(' || FIL.CD_EMPGCB || ');' || CHR(13) ||
       '        filialPK.setCodigoFilial(' || FIL.CD_FIL || ');' || CHR(13) ||       
       '        Filial filial' ||
       TRIM(TO_CHAR(ROW_NUMBER() OVER (ORDER BY FIL.CD_FIL ASC),'0000')) ||
       ' = new Filial(filialPK, "' ||        
       TRIM(FIL.NM_FIL) || '", "' ||
       TRIM(SUBSTR(TO_CHAR(FIL.CD_EMPGCB_CGC_RAI, '00000000'),1,3)) || '.' ||
       TRIM(SUBSTR(TO_CHAR(FIL.CD_EMPGCB_CGC_RAI, '00000000'),4,3)) || '.' ||
       TRIM(SUBSTR(TO_CHAR(FIL.CD_EMPGCB_CGC_RAI, '00000000'),7,3)) || '/' ||       
       TRIM(TO_CHAR(FIL.CD_FIL_CGC_FIL, '0000')) || '-' ||
       TRIM(TO_CHAR(FIL.CD_FIL_CGC_DVR, '00')) || '", ' ||
       FIL.CD_MUN || ');'  || CHR(13) ||
       '        filialService.addFilial(filial' || 
       TRIM(TO_CHAR(ROW_NUMBER() OVER (ORDER BY FIL.CD_FIL ASC),'0000')) || ');' 
       AS "Filial"
  FROM jtrach.FIL FIL
 WHERE FIL.CD_EST_SIG = 'ES'
   AND (FIL.DT_FIL_ABE_EST IS NOT NULL OR FIL.DT_FIL_ABE_FED IS NOT NULL) AND
       (FIL.DT_FIL_ENC_EST IS NULL OR FIL.DT_FIL_ENC_FED IS NULL)
   AND FIL.CD_EMPGCB  > 0
   AND TRIM(FIL.NM_FIL) <> ''
   AND FIL.CD_EMPGCB IN (SELECT DISTINCT GCB.CD_EMPGCB 
                           FROM jtrach.EMP_GCB GCB,
                                jtrach.FNE FNE
                          WHERE GCB.CD_EMPGCB_CGC_RAI = FNE.CD_EMPFNE_CGC_RAI
                            AND GCB.CD_EMPGCB         = FNE.CD_EMPGCB
                            AND GCB.CD_EMPGCB         > 0
                            AND GCB.DT_EMPGCB_ENC  IS NULL
                            AND (FNE.DT_FNE_ABE_EST IS NOT NULL OR
                                 FNE.DT_FNE_ABE_FED IS NOT NULL)
                            AND FNE.CD_GRPFNE = 16)               -- MARKETPLACE
 ORDER BY FIL.CD_EMPGCB, FIL.CD_FIL   
  WITH UR;
  

-- Carga de Microzonas
SELECT '        Microzona microzona' || 
       TRIM(TO_CHAR(ROW_NUMBER() OVER (ORDER BY MIC.CD_MICZEN ASC),'0000')) || 
       ' = new Microzona(null, ' || 
       '"' || UPPER(TRIM(MIC.NM_MICZEN)) || '", ' ||
       '"' || UPPER(MIC.ST_MICZEN) || '", ' ||
       '"' || UPPER(MIC.ST_MICZEN_ETG_DIA) || '", ' ||
       '"' || UPPER(MIC.ST_MICZEN_SEG) || '", ' ||
       '"' || UPPER(MIC.ST_MICZEN_TER) || '", ' ||
       '"' || UPPER(MIC.ST_MICZEN_QUA) || '", ' ||
       '"' || UPPER(MIC.ST_MICZEN_QUI) || '", ' ||
       '"' || UPPER(MIC.ST_MICZEN_SEX) || '", ' ||
       '"' || UPPER(MIC.ST_MICZEN_SAB) || '", ' ||
       'estado' || UPPER(MIC.CD_EST_SIG_ZEN) || ', ' ||
       'municipio' || UPPER(MIC.CD_EST_SIG_ZEN) || MIC.CD_MUN || ', ' ||
       MIC.CD_ROTETG || ');' || CHR(13) ||
       '        microzonaService.addMicrozona(microzona' ||
       TRIM(TO_CHAR(ROW_NUMBER() OVER (ORDER BY MIC.CD_MICZEN ASC),'0000')) || ');'     
       AS "Microzona"
  FROM jtrach.MIC_ZEN MIC
 WHERE MIC.CD_EST_SIG_ZEN = 'ES'
    AND MIC.CD_EMPGCB IN (SELECT DISTINCT GCB.CD_EMPGCB 
                           FROM jtrach.EMP_GCB GCB,
                                jtrach.FNE FNE
                          WHERE GCB.CD_EMPGCB_CGC_RAI = FNE.CD_EMPFNE_CGC_RAI
                            AND GCB.CD_EMPGCB         = FNE.CD_EMPGCB
                            AND GCB.CD_EMPGCB         > 0
                            AND GCB.DT_EMPGCB_ENC  IS NULL
                            AND (FNE.DT_FNE_ABE_EST IS NOT NULL OR
                                 FNE.DT_FNE_ABE_FED IS NOT NULL)
                            AND FNE.CD_GRPFNE = 16)               -- MARKETPLACE
    AND MIC.ST_MICZEN = 'A'
 ORDER BY MIC.CD_MICZEN
  WITH UR;
  

-- Carga de Rota de Entrega
SELECT  '        rotaEntregaPK.setCodigoRota(' || ROT.CD_ROTETG || '); ' || CHR(13) ||
        '        RotaEntrega rotaEntrega' || ROT.CD_ROTETG || ' = new RotaEntrega(rotaEntregaPK, "' ||
        TRIM(ROT.NM_ROTETG) || '", ' || 
        '"' || ROT.ST_ROTETG || '", ' ||
        ROT.CD_EMPGCB || ', ' ||
        ROT.CD_FIL || ', ' ||
        0 || ');' || CHR(13) ||  -- prazoExpedicao
        '        rotaEntregaService.addRotaEntrega(rotaEntrega' || ROT.CD_ROTETG || ');'
        AS "Rota_Entrega"
   FROM jtrach.ROT_ETG ROT
  WHERE ROT.CD_EST_SIG = 'ES'
    AND ROT.CD_EMPGCB IN (SELECT DISTINCT GCB.CD_EMPGCB 
                           FROM jtrach.EMP_GCB GCB,
                                jtrach.FNE FNE
                          WHERE GCB.CD_EMPGCB_CGC_RAI = FNE.CD_EMPFNE_CGC_RAI
                            AND GCB.CD_EMPGCB         = FNE.CD_EMPGCB
                            AND GCB.CD_EMPGCB         > 0
                            AND GCB.DT_EMPGCB_ENC  IS NULL
                            AND (FNE.DT_FNE_ABE_EST IS NOT NULL OR
                                 FNE.DT_FNE_ABE_FED IS NOT NULL)
                            AND FNE.CD_GRPFNE = 16)               -- MARKETPLACE
AND ROT.ST_ROTETG = 'A'                            
 ORDER BY ROT.CD_ROTETG  
  WITH UR;
  

-- Carga CEPs da Microzna
SELECT '        faixasCEPMicrozonaPK.setCodigoMicrozona(' || MUN.CD_MICZEN || ');' || CHR(13) ||
       '        faixasCEPMicrozonaPK.setCodigoSequencial(' ||
       -- TRIM(TO_CHAR(ROW_NUMBER() OVER (ORDER BY MUN.CD_MICZEN ASC),'0000')) || ');' || CHR(13) || ')';
       '1' || ');' || CHR(13) ||
       '        FaixasCEPMicrozona faixasCEPMicrozona' ||
       TRIM(TO_CHAR(ROW_NUMBER() OVER (ORDER BY MUN.CD_MICZEN ASC),'0000')) ||
       ' = new FaixasCEPMicrozona(faixasCEPMicrozonaPK, ' || 
       CD_MUN_FXA_IN1 || ', ' || CD_MUN_FXA_FI1 || ');' || CHR(13) ||
       '        faixasCEPMicrozonaService.addFaixasCEPMicrozona(faixasCEPMicrozona' || 
       TRIM(TO_CHAR(ROW_NUMBER() OVER (ORDER BY MUN.CD_MICZEN ASC),'0000')) || ');'
       AS "CEPs_Microzona"
  FROM jtrach.MUN MUN
 WHERE MUN.CD_EST_SIG_ZEN = 'ES'
   AND MUN.CD_MICZEN IS NOT NULL
  WITH UR;