<?xml version="1.0" encoding="UTF-8"?>

<!--/*******************************************************************************
     * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
     *******************************************************************************/-->

<!-- The XLS file that converts the GMF model file to ARM model file. -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dcase_gmf="http://www.dependable-os.net/2010/03/dcase/"
                xmlns:ARM="ARM"
                xmlns:xmi="http://www.omg.org/XMI"
                extension-element-prefixes="dcase_gmf"
                version="1.0">

  <xsl:output method="xml" encoding="UTF-8" indent="yes"/>

  <!--root element-->
  <xsl:template match="/">
    <ARM:Argument>
      <xsl:attribute name="xmi:version">2.0</xsl:attribute>
      <xsl:apply-templates select="dcase_gmf:Argument" />
    </ARM:Argument>
  </xsl:template>

  <xsl:template match="xsi:book">
    <xsl:value-of select="xsi:title" />
  </xsl:template>

  <xsl:template match="dcase_gmf:Argument">
    <xsl:call-template name="NodeElementAttribute" />
    <xsl:apply-templates select="rootBasicNode" mode="basic" />
    <xsl:apply-templates select="rootBasicLink" mode="basic" />
  </xsl:template>

  <!-- outputs the node element -->
  <xsl:template match="rootBasicNode" mode="basic">
    <xsl:choose>
        <!-- outputs to the ARM file -->
        <xsl:when test="@xsi:type='dcase:Goal'">
           <xsl:element name="containsArgumentElement">
             <xsl:call-template name="NodeType" />
             <xsl:call-template name="NodeElementAttribute" />
             <xsl:call-template name="CheckUndevelopNode">
               <xsl:with-param name="sourceid" select="concat('#',@id)" />
             </xsl:call-template>
           </xsl:element>
        </xsl:when>
        <xsl:when test="@xsi:type='dcase:Strategy'">
           <xsl:element name="containsArgumentElement">
             <xsl:call-template name="NodeType" />
             <xsl:call-template name="NodeElementAttribute" />
             <xsl:call-template name="CheckGoalNode">
               <xsl:with-param name="sourceid" select="concat('#',@id)" />
             </xsl:call-template>
           </xsl:element>
        </xsl:when>
        <xsl:when test="(@xsi:type='dcase:System') or (@xsi:type='dcase:Evidence') or (@xsi:type='dcase:Monitor') or (@xsi:type='dcase:Justification') or (@xsi:type='dcase:Context')">
           <xsl:element name="containsArgumentElement">
             <xsl:call-template name="NodeType" />
             <xsl:call-template name="NodeElementAttribute" />
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
    <xsl:if test="contains($sourceNodeType, 'dcase:Goal') or contains($sourceNodeType, 'dcase:Strategy') or contains($sourceNodeType, 'dcase:Evidence') or contains($sourceNodeType, 'dcase:Monitor') or contains($sourceNodeType, 'dcase:Justification') or contains($sourceNodeType, 'dcase:System') or contains($sourceNodeType, 'dcase:Context')">
      <xsl:if test="contains($targetNodeType, 'dcase:Goal') or contains($targetNodeType, 'dcase:Strategy') or contains($targetNodeType, 'dcase:Evidence') or contains($targetNodeType, 'dcase:Monitor') or contains($targetNodeType, 'dcase:Justification') or contains($targetNodeType, 'dcase:System') or contains($targetNodeType, 'dcase:Context')">
        <xsl:choose>
            <!-- outputs to the ARM file -->
            <xsl:when test="(@xsi:type='dcase:DcaseLink001') or (@xsi:type='dcase:DcaseLink003') or (@xsi:type='dcase:DcaseLink004')">
                <xsl:element name="containsArgumentLink">
                  <xsl:call-template name="LinkType" />
                  <xsl:call-template name="LinkElementAttribute" />
                </xsl:element>
            </xsl:when>
            <xsl:when test="@xsi:type='dcase:DcaseLink002'">
                <xsl:element name="containsArgumentLink">
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
    <xsl:attribute name="type" namespace="http://www.w3.org/2001/XMLSchema-instance">
      <!-- checks the name of link type -->
      <xsl:choose>
        <xsl:when test="(@xsi:type='dcase:Goal') or (@xsi:type='dcase:System')">ARM:Claim</xsl:when>
        <xsl:when test="@xsi:type='dcase:Strategy'">ARM:ArgumentReasoning</xsl:when>
        <xsl:when test="(@xsi:type='dcase:Context') or (@xsi:type='dcase:Justification') or (@xsi:type='dcase:Evidence') or (@xsi:type='dcase:Monitor')">ARM:InformationElement</xsl:when>
      </xsl:choose>
    </xsl:attribute>
  </xsl:template>

  <!-- sets the "xsi:type" attribute in the link element -->
  <xsl:template name="LinkType">
    <xsl:attribute name="type" namespace="http://www.w3.org/2001/XMLSchema-instance">
      <xsl:choose>
        <xsl:when test="@xsi:type='dcase:DcaseLink002'">ARM:AssertedContext</xsl:when>
        <xsl:when test="(@xsi:type='dcase:DcaseLink001') or (@xsi:type='dcase:DcaseLink003') or (@xsi:type='dcase:DcaseLink004')">
          <xsl:variable name="linkSource"><xsl:value-of select="substring-after(@source,'#')" /></xsl:variable>
          <xsl:variable name="linkTarget"><xsl:value-of select="substring-after(@target,'#')" /></xsl:variable>
          <xsl:choose>
            <xsl:when test="/dcase_gmf:Argument/rootBasicNode[(@id=$linkSource) and ((@xsi:type='dcase:Evidence') or (@xsi:type='dcase:Monitor'))]">ARM:AssertedEvidence</xsl:when>
            <xsl:when test="/dcase_gmf:Argument/rootBasicNode[(@id=$linkTarget) and ((@xsi:type='dcase:Evidence') or (@xsi:type='dcase:Monitor'))]">ARM:AssertedEvidence</xsl:when>
            <xsl:otherwise>ARM:AssertedInference</xsl:otherwise>
          </xsl:choose>
        </xsl:when>
      </xsl:choose>
    </xsl:attribute>
  </xsl:template>

  <!-- sets the "toBeSupported" attribute when the node type is 'Goal' node -->
  <xsl:template name="CheckUndevelopNode">
    <xsl:param name="sourceid">undefined</xsl:param>
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
    <xsl:attribute name="describes">
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
    <xsl:attribute name="identifier">
      <xsl:value-of select="@name"/>
    </xsl:attribute>
    <xsl:attribute name="content">
      <xsl:value-of select="@desc"/>
    </xsl:attribute>
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
    <xsl:attribute name="identifier">
      <xsl:value-of select="@name"/>
    </xsl:attribute>
  </xsl:template>

</xsl:stylesheet>

