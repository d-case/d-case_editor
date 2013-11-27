<?xml version="1.0" encoding="UTF-8"?>

<!--/*******************************************************************************
     * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
     *******************************************************************************/-->

<!--Transforms GMF to D-Case-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dcase_gmf="http://www.dependable-os.net/2013/11/dcase_model/"
                xmlns:dcase="http://www.dependable-os.net/2013/11/dcase"
                xmlns:dre="http://www.dependable-os.net/dre"
                xmlns:exsl="http://exslt.org/common"
                xmlns:dfunc="net.dependableos.dcase.diagram.common.xml.XsltExtFunctionUtil"
                extension-element-prefixes="exsl dre"
                version="1.0">

  <xsl:output method="xml" encoding="UTF-8" indent="yes"/>

  <!--Root-->
  <xsl:template match="/">
    <xsl:element name="dcase:dcase" namespace="http://www.dependable-os.net/2013/11/dcase">
      <xsl:apply-templates select="dcase_gmf:Argument"/>
    </xsl:element>
  </xsl:template>

  <xsl:template match="dcase_gmf:Argument">
    <!-- Descendant links of the root node -->
    <xsl:variable name="linkResult">
      <xsl:for-each select="rootBasicNode">
        <xsl:variable name="id" select="concat('#',@id)"/>
        <xsl:variable name="count" select="count(/dcase_gmf:Argument/rootBasicLink[@target=$id])"></xsl:variable>
        <xsl:if test="$count=0">
          <xsl:call-template name="RootBasicLink">
            <xsl:with-param name="source" select="$id"/>
          </xsl:call-template>
        </xsl:if>
      </xsl:for-each>
    </xsl:variable>

    <xsl:call-template name="ArgumentAttribute"/>
    <xsl:element name="dcase:description" namespace="http://www.dependable-os.net/2013/11/dcase">
      <xsl:value-of select="@desc"/>
    </xsl:element>
    <xsl:call-template name="Properties"/>
    <xsl:if test="@respName!='NaN' or @respAddress!='NaN' or @respIcon!='NaN'">
      <xsl:call-template name="Responsibilities"/>
    </xsl:if>
    <xsl:if test="@parameterVals!='NaN' and string-length(@parameterVals) &gt; 0 and @parameterDefs!='NaN' and string-length(@parameterDefs) &gt; 0">
      <xsl:call-template name="Parameters"/>
    </xsl:if>
    <xsl:element name="dcase:nodes" namespace="http://www.dependable-os.net/2013/11/dcase">
      <xsl:apply-templates select="rootBasicNode"/>
    </xsl:element>
      <!-- Links-->
    <xsl:element name="dcase:links" namespace="http://www.dependable-os.net/2013/11/dcase">
      <!-- Outputs descendant links of the root node eliminating duplicate links-->
      <xsl:for-each select="exsl:node-set($linkResult)/dcase:link[not(@id=preceding::dcase:link/@id)]">
        <xsl:copy-of select="."/>
      </xsl:for-each>

      <!-- Outputs rest of the links -->
      <xsl:apply-templates select="rootBasicLink[not(@id=exsl:node-set($linkResult)/dcase:link/@id)]" mode="basic"></xsl:apply-templates>
    </xsl:element>
  </xsl:template>

  <!--Node-->
  <xsl:template match="rootBasicNode">
    <xsl:element name="dcase:node" namespace="http://www.dependable-os.net/2013/11/dcase">
      <xsl:call-template name="BasicNodeAttribute"/>
      <xsl:element name="dcase:description" namespace="http://www.dependable-os.net/2013/11/dcase">
        <xsl:value-of select="@desc"/>
      </xsl:element>
      <xsl:call-template name="Properties"/>
      <xsl:if test="@respName!='NaN' or @respAddress!='NaN' or @respIcon!='NaN'">
        <xsl:call-template name="Responsibilities"/>
      </xsl:if>
      <xsl:if test="@parameterVals!='NaN' and string-length(@parameterVals) &gt; 0 and @parameterDefs!='NaN' and string-length(@parameterDefs) &gt; 0">
        <xsl:call-template name="Parameters"/>
      </xsl:if>
      <xsl:if test="@userdef011!='NaN' and string-length(@userdef011) &gt; 0">
        <xsl:call-template name="D-Script"/>
      </xsl:if>
    </xsl:element>
  </xsl:template>

  <!--Link (nodeset)-->
  <xsl:template name="RootBasicLink">
    <xsl:param name="ancestors"></xsl:param>
    <xsl:param name="source"></xsl:param>

    <!-- Ancestors -->
    <xsl:variable name="tmpAncestors">
      <xsl:copy-of select="$ancestors"/>
    </xsl:variable>

    <xsl:variable name="newAncestors">
      <xsl:copy-of select="$ancestors"/>
      <AncestorID>
        <xsl:value-of select="$source"/>
      </AncestorID>
    </xsl:variable>

    <!--Tests whether the link is looped-->
    <xsl:if test="not(exsl:node-set($tmpAncestors)/AncestorID[.=$source])">
      <xsl:apply-templates select="/dcase_gmf:Argument/rootBasicLink[(@source=$source) and (string(number(@siblingOrder)) != 'NaN')]" mode="basic">
        <xsl:sort select="number(@siblingOrder)" data-type="number" order="ascending"/>
      </xsl:apply-templates>
      <xsl:apply-templates select="/dcase_gmf:Argument/rootBasicLink[(@source=$source) and (string(number(@siblingOrder)) = 'NaN')]" mode="basic"/>

      <xsl:apply-templates select="/dcase_gmf:Argument/rootBasicLink[(@source=$source) and (string(number(@siblingOrder)) != 'NaN')]" mode="procChildren">
        <xsl:sort select="./@siblingOrder" data-type="number" order="ascending"/>
        <xsl:with-param name="ancestors" select="$newAncestors"/>
      </xsl:apply-templates>
      <xsl:apply-templates select="/dcase_gmf:Argument/rootBasicLink[(@source=$source) and (string(number(@siblingOrder)) = 'NaN')]" mode="procChildren">
        <xsl:with-param name="ancestors" select="$newAncestors"/>
      </xsl:apply-templates>
    </xsl:if>
  </xsl:template>

  <!--Link-->
  <xsl:template match="rootBasicLink" mode="basic">
    <xsl:element name="dcase:link" namespace="http://www.dependable-os.net/2013/11/dcase">
      <xsl:call-template name="BasicLinkAttribute"/>
      <xsl:element name="dcase:description" namespace="http://www.dependable-os.net/2013/11/dcase">
        <xsl:value-of select="@desc"/>
      </xsl:element>
      <xsl:call-template name="Properties"/>
    </xsl:element>
  </xsl:template>

  <xsl:template match="rootBasicLink" mode="procChildren">
    <xsl:param name="ancestors"></xsl:param>
    <xsl:call-template name="RootBasicLink">
      <xsl:with-param name="ancestors" select="$ancestors"/>
      <xsl:with-param name="source" select="./@target"/>
    </xsl:call-template>
  </xsl:template>


  <!--Attributes of the argument-->
  <xsl:template name="ArgumentAttribute">
    <xsl:apply-templates select="@id|@name|@status" mode="basic"/>
  </xsl:template>


  <!--Attributes of a node-->
  <xsl:template name="BasicNodeAttribute">
    <xsl:call-template name="NodeType"/>
    <xsl:apply-templates select="@id|@name|@status" mode="basic"/>
  </xsl:template>

  <!--Node type-->
  <xsl:template name="NodeType">
    <xsl:attribute name="type">
      <xsl:choose>
        <xsl:when test="@xsi:type='dcase:Goal'">Goal</xsl:when>
        <xsl:when test="@xsi:type='dcase:Strategy'">Strategy</xsl:when>
        <xsl:when test="@xsi:type='dcase:Evidence'">Evidence</xsl:when>
        <xsl:when test="@xsi:type='dcase:Undeveloped'">Undeveloped</xsl:when>
        <xsl:when test="@xsi:type='dcase:Context'">Context</xsl:when>
        <xsl:when test="@xsi:type='dcase:Monitor'">Monitor</xsl:when>
        <xsl:when test="@xsi:type='dcase:Justification'">Justification</xsl:when>
        <xsl:when test="@xsi:type='dcase:Userdef002'">Userdef002</xsl:when>
        <xsl:when test="@xsi:type='dcase:Userdef003'">Userdef003</xsl:when>
        <xsl:when test="@xsi:type='dcase:Assumption'">Assumption</xsl:when>
        <xsl:when test="@xsi:type='dcase:Module'">Module</xsl:when>
        <xsl:when test="@xsi:type='dcase:Contract'">Contract</xsl:when>
        <xsl:when test="@xsi:type='dcase:Action'">Action</xsl:when>
        <xsl:when test="@xsi:type='dcase:Pattern'">Pattern</xsl:when>
        <xsl:when test="@xsi:type='dcase:External'">External</xsl:when>
        <xsl:otherwise>undefined</xsl:otherwise>
      </xsl:choose>
    </xsl:attribute>
  </xsl:template>

  <!--Attributes of a link-->
  <xsl:template name="BasicLinkAttribute">
    <xsl:call-template name="LinkType"/>
    <xsl:apply-templates select="@id|@name|@status" mode="basic"/>
    <xsl:apply-templates select="@source|@target" mode="link"/>
  </xsl:template>

  <!--Link type-->
  <xsl:template name="LinkType">
    <xsl:attribute name="type">
      <xsl:choose>
        <xsl:when test="@xsi:type='dcase:SupportedBy'">SupportedBy</xsl:when>
        <xsl:when test="@xsi:type='dcase:InContextOf'">InContextOf</xsl:when>
        <xsl:when test="@xsi:type='dcase:Responsibility'">Responsibility</xsl:when>
        <xsl:when test="@xsi:type='dcase:DcaseLink004'">Link004</xsl:when>
        <xsl:otherwise>SupportedBy</xsl:otherwise>
      </xsl:choose>
    </xsl:attribute>
  </xsl:template>

  <!--Attributes-->
  <xsl:template match="@*" mode="basic">
    <xsl:attribute name="{local-name()}">
      <xsl:value-of select="."/>
    </xsl:attribute>
  </xsl:template>

  <!--Source or target of a link-->
  <xsl:template match="@*" mode="link">
    <xsl:attribute name="{local-name()}">
      <xsl:value-of select="substring-after(.,'#') "/>
    </xsl:attribute>
  </xsl:template>

  <!--Properties-->
  <xsl:template name="Properties">
    <xsl:element name="dcase:properties" namespace="http://www.dependable-os.net/2013/11/dcase">
      <xsl:apply-templates select="@attachment" mode="property">
        <xsl:with-param name="propertyName">Attachment</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@userdef001" mode="property">
        <xsl:with-param name="propertyName">Userdef001</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@userdef002" mode="property">
        <xsl:with-param name="propertyName">Userdef002</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@userdef003" mode="property">
        <xsl:with-param name="propertyName">Userdef003</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@userdef004" mode="property">
        <xsl:with-param name="propertyName">Userdef004</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@userdef005" mode="property">
        <xsl:with-param name="propertyName">Userdef005</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@userdef006" mode="property">
        <xsl:with-param name="propertyName">Userdef006</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@userdef007" mode="property">
        <xsl:with-param name="propertyName">Userdef007</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@userdef008" mode="property">
        <xsl:with-param name="propertyName">Userdef008</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@userdef009" mode="property">
        <xsl:with-param name="propertyName">Userdef009</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@userdef010" mode="property">
        <xsl:with-param name="propertyName">Userdef010</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@userdef011" mode="property">
        <xsl:with-param name="propertyName">Userdef011</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@userdef012" mode="property">
        <xsl:with-param name="propertyName">Userdef012</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@userdef013" mode="property">
        <xsl:with-param name="propertyName">Userdef013</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@userdef014" mode="property">
        <xsl:with-param name="propertyName">Userdef014</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@userdef015" mode="property">
        <xsl:with-param name="propertyName">Userdef015</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@userdef016" mode="property">
        <xsl:with-param name="propertyName">Userdef016</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@score" mode="property">
        <xsl:with-param name="propertyName">Score</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@weight" mode="property">
        <xsl:with-param name="propertyName">Weight</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@nodeLink" mode="property">
        <xsl:with-param name="propertyName">NodeLink</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@isNormal" mode="property">
        <xsl:with-param name="propertyName">IsNormal</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@stakeholder" mode="property">
        <xsl:with-param name="propertyName">Stakeholder</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@riskAnalysis" mode="property">
        <xsl:with-param name="propertyName">RiskAnalysis</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@message" mode="property">
        <xsl:with-param name="propertyName">Message</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@requirement" mode="property">
        <xsl:with-param name="propertyName">Requirement</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@parameterizedDesc" mode="property">
        <xsl:with-param name="propertyName">ParameterizedDesc</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@parent" mode="property">
        <xsl:with-param name="propertyName">Parent</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@refSource" mode="property">
        <xsl:with-param name="propertyName">RefSource</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@flag" mode="property">
        <xsl:with-param name="propertyName">Flag</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@leafNode" mode="property">
        <xsl:with-param name="propertyName">LeafNode</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@i" mode="property">
        <xsl:with-param name="propertyName">I</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@j" mode="property">
        <xsl:with-param name="propertyName">J</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@siblingOrder" mode="property">
        <xsl:with-param name="propertyName">SiblingOrder</xsl:with-param>
      </xsl:apply-templates>
      <xsl:apply-templates select="@validUntil" mode="property">
        <xsl:with-param name="propertyName">ValidUntil</xsl:with-param>
      </xsl:apply-templates>
    </xsl:element>
  </xsl:template>


  <!--Property values-->
  <xsl:template match="@*" mode="property">
    <xsl:param name="propertyName">undefined</xsl:param>
    <xsl:element name="dcase:property" namespace="http://www.dependable-os.net/2013/11/dcase">
      <xsl:attribute name="name">
        <xsl:value-of select="$propertyName"/>
      </xsl:attribute>
      <xsl:attribute name="value">
        <xsl:value-of select="."/>
      </xsl:attribute>
    </xsl:element>
  </xsl:template>

  <!--Responsibilities-->
  <xsl:template name="Responsibilities">
    <xsl:element name="dcase:responsibility" namespace="http://www.dependable-os.net/2013/11/dcase">
      <xsl:if test="@respName!='NaN'">
        <xsl:attribute name="name">
          <xsl:value-of select="@respName"/>
        </xsl:attribute>
      </xsl:if>
      <xsl:if test="@respAddress!='NaN'">
        <xsl:attribute name="address">
          <xsl:value-of select="@respAddress"/>
        </xsl:attribute>
      </xsl:if>
      <xsl:if test="@respIcon!='NaN'">
        <xsl:attribute name="icon">
          <xsl:value-of select="@respIcon"/>
        </xsl:attribute>
      </xsl:if>
    </xsl:element>
  </xsl:template>

  <!--Parameters-->
  <xsl:template name="Parameters">
    <xsl:copy-of select="dfunc:deparameterize(@parameterVals, @parameterDefs)"/>
  </xsl:template>

  <!-- d-script element -->
  <xsl:template name="D-Script">
    <xsl:copy-of select="dfunc:deserialize(@userdef011)"/>
  </xsl:template>

</xsl:stylesheet>
