<?xml version="1.0" encoding="UTF-8"?>

<!--
 * Copyright (C) 2013  The University of Electro-Communications All rights reserved.
 * Copyright (C) 2013  AXE,Inc.
-->

<!-- Transforms Old GMF to New GMF -->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dcase_old="http://www.dependable-os.net/2010/03/dcase/"
                xmlns:dcase="http://www.dependable-os.net/2013/11/dcase_model/"
                version="1.0">

  <xsl:output method="xml" encoding="UTF-8" indent="yes" />

  <!-- Root -->
  <xsl:template match="/">
    <dcase:Argument>
      <xsl:apply-templates select="dcase_old:Argument"/>
    </dcase:Argument>
  </xsl:template>

  <xsl:template match="dcase_old:Argument">
    <xsl:apply-templates select="@*" mode="basicnode"/>
    <xsl:apply-templates select="rootBasicNode"/>
    <xsl:apply-templates select="rootBasicLink"/>
  </xsl:template>

  <!-- Node -->
  <xsl:template match="rootBasicNode">
    <xsl:element name="rootBasicNode">
      <xsl:apply-templates select="@*" mode="node"/>
    </xsl:element>
  </xsl:template>

  <!-- Link -->
  <xsl:template match="rootBasicLink">
    <xsl:element name="rootBasicLink">
      <xsl:apply-templates select="@*" mode="basiclink"/>
    </xsl:element>
  </xsl:template>

  <!-- Attributes -->

  <xsl:template match="@*" mode="basicnode">
    <xsl:choose>
      <xsl:when test="name(.)='userdef005'">
        <xsl:attribute name="parameterizedDesc"><xsl:value-of select="."/></xsl:attribute>
        <xsl:attribute name="{name(.)}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:when>
      <xsl:when test="name(.)='userdef002'">
        <xsl:attribute name="message"><xsl:value-of select="."/></xsl:attribute>
        <xsl:attribute name="{name(.)}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:when>
      <xsl:when test="name(.)='userdef003'">
        <xsl:attribute name="requirement"><xsl:value-of select="."/></xsl:attribute>
        <xsl:attribute name="{name(.)}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:when>
      <xsl:when test="name(.)='userdef009'">
        <xsl:attribute name="parameterDefs"><xsl:value-of select="."/></xsl:attribute>
        <xsl:attribute name="{name(.)}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:when>
      <xsl:when test="name(.)='userdef007'">
        <xsl:attribute name="parameterVals"><xsl:value-of select="."/></xsl:attribute>
        <xsl:attribute name="{name(.)}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:when>
      <xsl:when test="name(.)='userdef013'">
        <xsl:attribute name="parent"><xsl:value-of select="."/></xsl:attribute>
        <xsl:attribute name="{name(.)}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:when>
      <xsl:when test="name(.)='userdef011'">
        <xsl:attribute name="refSource"><xsl:value-of select="."/></xsl:attribute>
        <xsl:attribute name="{name(.)}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:when>
      <xsl:when test="name(.)='userdef015'">
        <xsl:attribute name="flag"><xsl:value-of select="."/></xsl:attribute>
        <xsl:attribute name="{name(.)}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:when>
      <xsl:when test="name(.)='userdef012'">
        <xsl:attribute name="respName"><xsl:value-of select="substring-before(.,';')"/></xsl:attribute>
        <xsl:variable name="respRes" select="substring-after(.,';')"/>
        <xsl:attribute name="respAddress"><xsl:value-of select="substring-before($respRes,';')"/></xsl:attribute>
        <xsl:attribute name="respIcon"><xsl:value-of select="substring-after($respRes,';')"/></xsl:attribute>
        <xsl:attribute name="{name(.)}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:when>
      <xsl:otherwise>
        <xsl:attribute name="{name(.)}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <xsl:template match="@*" mode="node">
    <xsl:if test="name(.)='xsi:type'">
      <xsl:attribute name="{name(.)}">
        <xsl:choose>
          <xsl:when test=".='dcase:Userdef004'">dcase:Assumption</xsl:when>
          <xsl:when test=".='dcase:Userdef005'">dcase:Module</xsl:when>
          <xsl:when test=".='dcase:Userdef006'">dcase:Contract</xsl:when>
          <xsl:when test=".='dcase:System'">dcase:Pattern</xsl:when>
          <xsl:when test=".='dcase:Policy'">dcase:Action</xsl:when>
          <xsl:when test=".='dcase:Userdef001'">dcase:External</xsl:when>
          <xsl:otherwise><xsl:value-of select="."/></xsl:otherwise>
        </xsl:choose>
      </xsl:attribute>
    </xsl:if>
    <xsl:if test="name(.)!='xsi:type'">
      <xsl:apply-templates select="." mode="basicnode"/>
    </xsl:if>
  </xsl:template>

  <xsl:template match="@*" mode="basiclink">
    <xsl:choose>
      <xsl:when test="name(.)='xsi:type'">
        <xsl:attribute name="{name(.)}">
          <xsl:choose>
            <xsl:when test=".='dcase:DcaseLink001'">dcase:SupportedBy</xsl:when>
            <xsl:when test=".='dcase:DcaseLink002'">dcase:InContextOf</xsl:when>
            <xsl:when test=".='dcase:DcaseLink003'">dcase:Responsibility</xsl:when>
            <xsl:otherwise><xsl:value-of select="."/></xsl:otherwise>
          </xsl:choose>
        </xsl:attribute>
      </xsl:when>
      <xsl:when test="name(.)='userdef001'">
        <xsl:attribute name="siblingOrder"><xsl:value-of select="."/></xsl:attribute>
        <xsl:attribute name="{name(.)}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:when>
      <xsl:when test="name(.)='userdef002'">
        <xsl:attribute name="message"><xsl:value-of select="."/></xsl:attribute>
        <xsl:attribute name="{name(.)}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:when>
      <xsl:otherwise>
        <xsl:attribute name="{name(.)}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

</xsl:stylesheet>
