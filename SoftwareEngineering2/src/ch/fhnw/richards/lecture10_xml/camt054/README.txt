This is a real, functional prototype for parsing PostFinance XML files.

PostFinance changed the format for payment information to XML (ISO 20022,
camt.054) in 2017. This prototype was constructed as a first step
towards adapting an ERP system to read the new format. The ISO standard
includes lots of information, only a small portion of which was needed for
this particular system.

Of particular importance in Switzerland is the ESR-Reference number, which
(of course) is not part of the international standard. PostFinance supplies
the ESR number in the tag <RmtInf><Strd><CdtrRefInf><Ref> in each transaction.

The definition of the camt.054 format (as an XML Schema) is included in the
document "camt.054.001.04.xsd". This is rather difficult to read, and can be
interpreted better with the help of the implementation guidelines (PDF), and
also this link: https://www.postfinance.ch/de/biz/zv/download/basic.html

To get a quick overview, have a look at the sample XML file.