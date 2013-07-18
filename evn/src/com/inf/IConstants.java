package com.inf;


import com.inf.admin.IConstantsAdmin;

public interface IConstants extends IConstantsAdmin{

    public static final String APP_SEPARATE_ = "|";
    public static final String[] HEADER_DOCS_REVIEW={"form.docs.formId","form.docs.dossierId","form.docs.localCode","form.docs.docCode","form.docs.localDate","form.docs.docDate","form.docs.statusId","form.docs.fromId","form.docs.abstracts","form.docs.storeAgeId","form.docs.secureId","form.docs.signer","form.docs.expressId","form.docs.viaId","form.docs.docsTypeId","form.docs.deadLine","form.docs.address","form.docs.kyNhan"};
    public static final String[] HEADER_REPORT_RECV_DOCS={"S&#7893; c&#244;ng v&#259;n","H&#7891; s&#417;","S&#7889; &#273;&#7871;n","S&#7889; c&#244;ng v&#259;n","Ng&#224;y &#273;&#7871;n","Ng&#224;y c&#244;ng v&#259;n","Tr&#7841;ng th&#225;i","N&#417;i g&#7917;i","Tr&#237;ch y&#7871;u","N&#417;i nh&#7853;n","&#272;&#7897; m&#7853;t","Ng&#432;&#7901;i k&#253;","&#272;&#7897; kh&#7849;n","H&#236;nh th&#7913;c chuy&#7875;n","Lo&#7841;i v&#259;n b&#7843;n","H&#7841;n x&#7917; l&#237;","&#272;&#7883;a ch&#7881;","K&#253; nh&#7853;n"};
    public static final String[] HEADER_REPORT_SEND_DOCS={"S&#7893; c&#244;ng v&#259;n","H&#7891; s&#417;","S&#7889; &#273;i","S&#7889; c&#244;ng v&#259;n","Ng&#224;y &#273;i","Ng&#224;y c&#244;ng v&#259;n","Tr&#7841;ng th&#225;i","N&#417;i nh&#7853;n","Tr&#237;ch y&#7871;u","N&#417;i l&#432;u","&#272;&#7897; m&#7853;t","Ng&#432;&#7901;i k&#253;","&#272;&#7897; kh&#7849;n","H&#236;nh th&#7913;c chuy&#7875;n","Lo&#7841;i v&#259;n b&#7843;n","H&#7841;n x&#7917; l&#237;","&#272;&#7883;a ch&#7881;","N&#417;i l&#432;u g&#7889;c","S&#7889; ban","Ghi ch&#250;"};    
    public static final String[] WORLFLOW_REPORT={"C&#244;ng v&#259;n &#273;&#7871;n","C&#244;ng v&#259;n &#273;i"};
    public static final String STATUS_NOT_CHECKED="-1"; 
    
    public static final int monday   = 1;
    public static final int tueday   = 2;
    public static final int wedday   = 4;
    public static final int wday     = 8;
    public static final int thuday   = 16;
    public static final int friday   = 32;
    public static final int satday   = 64;
    public static final int subday   = 128;
}
