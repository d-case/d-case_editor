<?xml version="1.0" encoding="UTF-8"?>

<!--
 * Copyright (C) 2013,2014  The University of Electro-Communications All rights reserved.
 * Copyright (C) 2013,2014  AXE,Inc.
-->

<!-- The XLS file that converts the GMF model file to SACM model file. -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dcase_gmf="http://www.dependable-os.net/2013/11/dcase_model/"
                xmlns:ARM="www.omg.org/spec/SACM/20120501/Argumentation"
                xmlns:SACM="www.omg.org/spec/SACM/20120501"
                xmlns:xmi="http://www.omg.org/XMI"
                extension-element-prefixes="dcase_gmf"
                version="1.0">

  <xsl:output method="xml" encoding="UTF-8" indent="yes"/>

  <!--root element-->
  <xsl:template match="/">
    <ARM:Argumentation>
      <xsl:attribute name="xmi:version">2.0</xsl:attribute>
      <xsl:apply-templates select="dcase_gmf:Argument" />
    </ARM:Argumentation>
  </xsl:template>

  <xsl:template match="xsi:book">
    <xsl:value-of select="xsi:title" />
  </xsl:template>

  <xsl:template match="dcase_gmf:Argument">
    <xsl:call-template name="NodeElementAttribute" />
    <xsl:if test="@parent!=''">
      <xsl:attribute name="parent">
        <xsl:value-of select="@parent"/>
      </xsl:attribute>
    </xsl:if>
    <xsl:apply-templates select="rootBasicNode" mode="basic" />
    <xsl:apply-templates select="rootBasicLink" mode="basic" />
  </xsl:template>

  <!-- outputs the node element -->
  <xsl:template match="rootBasicNode" mode="basic">
    <xsl:choose>
        <!-- outputs to the SACM file -->
        <xsl:when test="@xsi:type='dcase:Goal'">
           <xsl:element name="argumentElement">
             <xsl:call-template name="NodeType" />
             <xsl:call-template name="NodeElementAttribute" />
             <xsl:call-template name="CheckUndevelopNode">
               <xsl:with-param name="sourceid" select="concat('#',@id)" />
             </xsl:call-template>
           </xsl:element>
        </xsl:when>
        <xsl:when test="@xsi:type='dcase:Strategy'">
           <xsl:element name="argumentElement">
             <xsl:call-template name="NodeType" />
             <xsl:call-template name="NodeElementAttribute" />
             <xsl:call-template name="CheckGoalNode">
               <xsl:with-param name="sourceid" select="concat('#',@id)" />
             </xsl:call-template>
           </xsl:element>
        </xsl:when>
        <xsl:when test="(@xsi:type='dcase:Evidence') or (@xsi:type='dcase:Monitor') or (@xsi:type='dcase:Justification') or (@xsi:type='dcase:Context') or (@xsi:type='dcase:Assumption') or (@xsi:type='dcase:Module') or (@xsi:type='dcase:Contract') or (@xsi:type='dcase:Pattern') or (@xsi:type='dcase:Action') or (@xsi:type='dcase:External')">
           <xsl:element name="argumentElement">
             <xsl:call-template name="NodeType" />
             <xsl:call-template name="NodeElementAttribute" />
             <xsl:if test="(@xsi:type='dcase:Module') or (@xsi:type='dcase:External')">
               <xsl:attribute name="assumed">true</xsl:attribute>
               <xsl:attribute name="toBeSupported">false</xsl:attribute>
             </xsl:if>
             <xsl:if test="(@xsi:type='dcase:Context') or (@xsi:type='dcase:Justification') or (@xsi:type='dcase:Evidence') or (@xsi:type='dcase:Monitor') or (@xsi:type='dcase:Assumption') or (@xsi:type='dcase:Pattern') or (@xsi:type='dcase:Action')">
               <xsl:attribute name="url"/>
             </xsl:if>
             <xsl:if test="@xsi:type='dcase:Pattern'">
               <xsl:if test="@subType!=''">
                 <xsl:attribute name="subType">
                   <xsl:value-of select="@subType"/>
                 </xsl:attribute>
               </xsl:if>
               <xsl:if test="@leafNode!=''">
                 <xsl:attribute name="leafNode">
                   <xsl:value-of select="@leafNode"/>
                 </xsl:attribute>
               </xsl:if>
               <xsl:if test="@i!=''">
                 <xsl:attribute name="i">
                   <xsl:value-of select="@i"/>
                 </xsl:attribute>
               </xsl:if>
               <xsl:if test="@j!=''">
                 <xsl:attribute name="j">
                   <xsl:value-of select="@j"/>
                 </xsl:attribute>
               </xsl:if>
               <xsl:if test="@parameterDefs!=''">
                 <xsl:attribute name="parameterDefs">
                   <xsl:value-of select="@parameterDefs"/>
                 </xsl:attribute>
               </xsl:if>
               <xsl:if test="@parameterVals!=''">
                 <xsl:attribute name="parameterVals">
                   <xsl:value-of select="@parameterVals"/>
                 </xsl:attribute>
               </xsl:if>
             </xsl:if>
           </xsl:element>
        </xsl:when>
    </xsl:choose>
  </xsl:template>

  <!-- outputs the link element -->
  <xsl:template match="rootBasicLink" mode="basic">
    <xsl:variable name="linkSource"><xsl:value-of select="substring-after(@source,'#')" /></xsl:variable>
    <xsl:variable name="linkTarget"><xsl:value-of select="substring-after(@target,'#')" /></xsl:variable>
    <!-- gets the name of node type -->
    <xsl:variable name="sourceNodeType">
        <xsl:value-of select="/dcase_gmf:Argument/rootBasicNode[@id=$linkSource]/@xsi:type" />
    </xsl:variable>
    <xsl:variable name="targetNodeType">
        <xsl:value-of select="/dcase_gmf:Argument/rootBasicNode[@id=$linkTarget]/@xsi:type" />
    </xsl:variable>
    <!-- checks the name of node type -->
    <xsl:if test="contains($sourceNodeType, 'dcase:Goal') or contains($sourceNodeType, 'dcase:Strategy') or contains($sourceNodeType, 'dcase:Evidence') or contains($sourceNodeType, 'dcase:Monitor') or contains($sourceNodeType, 'dcase:Justification') or contains($sourceNodeType, 'dcase:Context') or contains($sourceNodeType, 'dcase:Assumption') or contains($sourceNodeType, 'dcase:Module') or contains($sourceNodeType, 'dcase:Contract') or contains($sourceNodeType, 'dcase:Pattern') or contains($sourceNodeType, 'dcase:Action') or contains($sourceNodeType, 'dcase:External')">
      <xsl:if test="contains($targetNodeType, 'dcase:Goal') or contains($targetNodeType, 'dcase:Strategy') or contains($targetNodeType, 'dcase:Evidence') or contains($targetNodeType, 'dcase:Monitor') or contains($targetNodeType, 'dcase:Justification') or contains($targetNodeType, 'dcase:Context') or contains($targetNodeType, 'dcase:Assumption') or contains($targetNodeType, 'dcase:Module') or contains($targetNodeType, 'dcase:Contract') or contains($targetNodeType, 'dcase:Pattern') or contains($targetNodeType, 'dcase:Action') or contains($targetNodeType, 'dcase:External')">
        <xsl:choose>
            <!-- outputs to the SACM file -->
            <xsl:when test="(@xsi:type='dcase:SupportedBy') or (@xsi:type='dcase:Responsibility') or (@xsi:type='dcase:DcaseLink004')">
                <xsl:element name="argumentElement">
                  <xsl:call-template name="LinkType" />
                  <xsl:call-template name="LinkElementAttribute" />
                </xsl:element>
            </xsl:when>
            <xsl:when test="@xsi:type='dcase:InContextOf'">
                <xsl:element name="argumentElement">
                  <xsl:call-template name="LinkType" />
                  <xsl:call-template name="LinkElementAttribute" />
                </xsl:element>
            </xsl:when>
        </xsl:choose>
      </xsl:if>
    </xsl:if>
  </xsl:template>

  <!-- sets the "xsi:type" attribute in the node element -->
  <xsl:template name="NodeType">
    <xsl:attribute name="xsi:type">
      <!-- checks the name of link type -->
      <xsl:choose>
        <xsl:when test="(@xsi:type='dcase:Goal') or (@xsi:type='dcase:Module') or (@xsi:type='dcase:External')">ARM:Claim</xsl:when>
        <xsl:when test="@xsi:type='dcase:Strategy'">ARM:ArgumentReasoning</xsl:when>
        <xsl:when test="(@xsi:type='dcase:Context') or (@xsi:type='dcase:Justification') or (@xsi:type='dcase:Evidence') or (@xsi:type='dcase:Monitor') or (@xsi:type='dcase:Assumption') or (@xsi:type='dcase:Pattern') or (@xsi:type='dcase:Action')">ARM:InformationElement</xsl:when>
      </xsl:choose>
    </xsl:attribute>
  </xsl:template>

  <!-- sets the "xsi:type" attribute in the link element -->
  <xsl:template name="LinkType">
    <xsl:attribute name="xsi:type">
      <xsl:choose>
        <xsl:when test="@xsi:type='dcase:InContextOf'">ARM:AssertedContext</xsl:when>
        <xsl:when test="(@xsi:type='dcase:SupportedBy') or (@xsi:type='dcase:Responsibility') or (@xsi:type='dcase:DcaseLink004')">
          <xsl:variable name="linkSource"><xsl:value-of select="substring-after(@source,'#')" /></xsl:variable>
          <xsl:variable name="linkTarget"><xsl:value-of select="substring-after(@target,'#')" /></xsl:variable>
          <xsl:choose>
            <xsl:when test="/dcase_gmf:Argument/rootBasicNode[(@id=$linkSource) and ((@xsi:type='dcase:Evidence') or (@xsi:type='dcase:Monitor') or (@xsi:type='dcase:Action'))]">ARM:AssertedEvidence</xsl:when>
            <xsl:when test="/dcase_gmf:Argument/rootBasicNode[(@id=$linkTarget) and ((@xsi:type='dcase:Evidence') or (@xsi:type='dcase:Monitor') or (@xsi:type='dcase:Action'))]">ARM:AssertedEvidence</xsl:when>
            <xsl:otherwise>ARM:AssertedInference</xsl:otherwise>
          </xsl:choose>
        </xsl:when>
      </xsl:choose>
    </xsl:attribute>
  </xsl:template>

  <!-- sets the "toBeSupported" attribute when the node type is 'Goal' node -->
  <xsl:template name="CheckUndevelopNode">
    <xsl:param name="sourceid">undefined</xsl:param>
    <xsl:attribute name="assumed">false</xsl:attribute>
    <xsl:attribute name="toBeSupported">false</xsl:attribute>
    <xsl:for-each select="/dcase_gmf:Argument/rootBasicLink[@source=$sourceid]">
      <xsl:variable name="targetid" select="substring-after(@target,'#')" />
      <xsl:variable name="count" select="count(/dcase_gmf:Argument/rootBasicNode[@id=$targetid and @xsi:type='dcase:Undeveloped'])" />
      <xsl:if test="$count &gt; 0"> 
        <xsl:attribute name="toBeSupported">true</xsl:attribute>
      </xsl:if>
    </xsl:for-each>
  </xsl:template>

  <!-- sets the "describes" attribute when the node type is 'Strategy' node -->
  <xsl:template name="CheckGoalNode">
    <xsl:param name="sourceid">undefined</xsl:param>
    <xsl:variable name="describesAttributes">
      <xsl:for-each select="/dcase_gmf:Argument/rootBasicLink[@source=$sourceid]">
          <xsl:variable name="targetid" select="substring-after(@target,'#')" />
          <xsl:variable name="describesValue">
            <xsl:apply-templates select="/dcase_gmf:Argument/rootBasicNode[@id=$targetid and @xsi:type='dcase:Goal']" mode="describes" />
          </xsl:variable>
          <xsl:value-of select="$describesValue" />
        </xsl:for-each>
    </xsl:variable>
    <xsl:variable name="describesCount">
      <xsl:value-of select="string-length($describesAttributes)" />
    </xsl:variable>
    <xsl:attribute name="describedInference">
      <xsl:value-of select="substring($describesAttributes, 0, $describesCount)" />
    </xsl:attribute>
  </xsl:template>

  <!-- adds the space character to the "describes" attribute -->
  <xsl:template match="rootBasicNode" mode="describes">
    <xsl:value-of select="@id" /><xsl:text  disable-output-escaping="yes"> </xsl:text>
  </xsl:template>

  <!-- sets the attributes in the element of the node -->
  <xsl:template name="NodeElementAttribute">
    <xsl:attribute name="xmi:id">
      <xsl:value-of select="@id"/>
    </xsl:attribute>
    <xsl:attribute name="id">
      <xsl:value-of select="@name"/>
    </xsl:attribute>
    <xsl:attribute name="content">
      <xsl:value-of select="@desc"/>
    </xsl:attribute>
    <xsl:attribute name="parameterizedContent">
      <xsl:value-of select="@parameterizedDesc"/>
    </xsl:attribute>
    <xsl:if test="@flag!=''">
      <xsl:attribute name="flag">
        <xsl:value-of select="@flag"/>
      </xsl:attribute>
    </xsl:if>
    <xsl:if test="@refSource!=''">
      <xsl:attribute name="refSource">
        <xsl:value-of select="@refSource"/>
      </xsl:attribute>
    </xsl:if>
    <xsl:if test="@attachment!=''">
      <xsl:attribute name="attachment">
        <xsl:value-of select="@attachment"/>
      </xsl:attribute>
    </xsl:if>
    <xsl:attribute name="description"/>
  </xsl:template>

  <!-- sets the attributes in the element of the link -->
  <xsl:template name="LinkElementAttribute">
    <xsl:attribute name="xmi:id">
      <xsl:value-of select="@id"/>
    </xsl:attribute>
    <xsl:attribute name="content">
      <xsl:value-of select="@desc"/>
    </xsl:attribute>
    <xsl:attribute name="source">
      <xsl:value-of select="substring-after(@source,'#')"/>
    </xsl:attribute>
    <xsl:attribute name="target">
      <xsl:value-of select="substring-after(@target,'#')"/>
    </xsl:attribute>
    <xsl:attribute name="id">
      <xsl:value-of select="@name"/>
    </xsl:attribute>
    <xsl:attribute name="description"/>
  </xsl:template>

</xsl:stylesheet>

