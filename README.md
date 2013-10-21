# D-Case Editor - A Typed Assurance Case Editor

D-Case Editor is a typed assurance case editor. which has been implemented as an Eclips plug-in using Eclipse GMF. The key features are as follows.

* Supports GSN ( Goal Structuring Notation )
* GSN Pattern Library use and prototype Type Checking function
* Consistency Checking of D-Case description  
  Goto [Makoto Takeyama's D-Case/Agda Page](http://wiki.portal.chalmers.se/agda/pmwiki.php?n=D-Case-Agda.D-Case-Agda)
* Monitoring of target systems

[For more information.](http://www.dcase.jp/index.html) (japanese page)

## Information

#### What is an "assurance case"?

System assurance has become very important in many industrial sectors. Submission of safety cases (assurance cases for safety of systems) to certification bodies is required when developing and operating safety critical systems, e. g., automotive, railway, defense, nuclear plants and sea oils. There are several standards, e. g. EUROCONTROL [1], Rail Yellow Book [2] and MoD Defence Standard 00-56, which mandate the use of safety cases. Furthermore, recently the medical device industry has also been requiring assurance cases [4].  
There are several definitions of "assurance case" [3]. One such definition is as follows [5].  
"a documented body of evidence that provides a convincing and valid argument that a system is adequately dependable for a given application in a given environment."

[1] EuropeanOrganisationfortheSafetyofAirNavigation. Safety Case Development Manual. European Air Traffic Management, 2006  
[2] Railtrack. Yellow Book 3. Engineering Safety Management Issue3, Vol. 1, Vol. 2, 2000  
[3] Workshop on Assurance Cases: Best Practices, Possible, Obstacles, and Future Opportunities, DSN 2004, 2004  
[4] <http://www.fda.gov/MedicalDevices/DeviceRegulationandGuidance/GuidanceDocuments/ucm206153.htm#6>  
[5] <http://www.city.ac.uk/informatics/school-organisation/centre-for-software-reliability/research>

#### What is GSN (Goal Structuring Notation) ?

GSN is a major graphical notation for assurance cases. It was developed by Tim Kelly and his colleagues at the University of York. See Tim's introduction paper:  
[The Goal Structuring Notation - A Safety Argument Notation](http://www-users.cs.york.ac.uk/~tpk/dsn2004.pdf)  
T P Kelly, R A Weaver  
in Proceedings of the Dependable Systems and Networks 2004 Workshop on Assurance Cases, July 2004

#### What is D-Case?

D-Case is the name of our research team for assurance cases. The letter "D" emphasize our focus on **D**ependability. Our team is one of the DEOS project teams. DEOS aims to develop dependable embedded systems.We observe assurance cases are crucial for sustaining dependability of systems in an open environment. For more details, please refer to our [Inconsistency](http://call.robust11.org/) Robustness 2011 paper ( the paper is available from the web page ) .

## Environment in which operation is verified

Note: Currently, we only officially support use of D-Case Editor in Eclipse IDE for Java Developers (Indigo Service Release 1) on WindowsR 7, but some people kindly tell us that D-Case Editor can be installed and seems to work fine in the following environments: Eclipse 3.5(Galileo) on OS X (10.6, 10.7, and 10.8), Debian on GNU/Linux ( 6.0.3 ) , and Eclipse 3.6(Helios) on Redhat Linux. If possible, please let us know how it works in your environment :- )

## License

see [LICENSE.md](https://github.com/d-case/d-case_editor/blob/master/LICENSE.md).

## Papers

* Hajime Fujita, Yutaka Matsuno, Toshihiro Hanawa, Mitsuhisa Sato, Shinpei Kato, and Yutaka Ishikawa, "DS-Bench Toolset: Tools for Dependability Benchmarking with Simulation and Assurance,"42nd IEEE/IFIP International Conference on Dependable System and Networks (DSN 2012),8 pages,Jun.2012.(Accepted).
* Yutaka Matsuno, Kenji Taguchi: Parameterised Argument Structure for GSN Patterns, Proc. IEEE 11th International Conference on Quality Software (QSIC 2011), 6 pages, to appear, July 2011 (Short Paper).
* Yutaka Matsuno, Hiroki Takamura, Yutaka Ishikawa: A Dependability Case Editor with Pattern Library, Proc. IEEE 12th International Symposium on High-Assurance Systems Engineering (HASE), pp. 170-171, Nov. 2010 (Fast Abstract).
