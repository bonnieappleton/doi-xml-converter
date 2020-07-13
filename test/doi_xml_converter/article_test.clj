(ns doi-xml-converter.article-test
  (:require [clojure.test :refer :all]
            [doi-xml-converter.article :refer :all]
            [clj-http.client :as client]))

(def xml-from-crossref "<?xml version=\"1.0\" encoding=\"UTF-8\"?>
        <crossref_result xmlns=\"http://www.crossref.org/qrschema/3.0\" version=\"3.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.crossref.org/qrschema/3.0 http://www.crossref.org/schemas/crossref_query_output3.0.xsd\">\r
          <query_result>\r
            <head>\r
              <doi_batch_id>none</doi_batch_id>\r
            </head>\r
            <body>\r
              <query status=\"resolved\">\r
                <doi type=\"journal_article\">10.5555/12345678</doi>\r
                <crm-item name=\"publisher-name\" type=\"string\">Society of Psychoceramics</crm-item>\r
                <crm-item name=\"prefix-name\" type=\"string\">Society of Psychoceramics</crm-item>\r
                <crm-item name=\"member-id\" type=\"number\">17333</crm-item>\r
                <crm-item name=\"citation-id\" type=\"number\">51518055</crm-item>\r
                <crm-item name=\"journal-id\" type=\"number\">151905</crm-item>\r
                <crm-item name=\"deposit-timestamp\" type=\"number\">20181015142652436</crm-item>\r
                <crm-item name=\"owner-prefix\" type=\"string\">10.32013</crm-item>\r
                <crm-item name=\"last-update\" type=\"date\">2019-07-25T14:55:14Z</crm-item>\r
                <crm-item name=\"created\" type=\"date\">2011-11-09T09:42:05Z</crm-item>\r
                <crm-item name=\"citedby-count\" type=\"number\">2</crm-item>\r
                <crm-item name=\"relation\" type=\"doi\" claim=\"corrigendum\">10.5555/12345678</crm-item>\r
                <crm-item name=\"relation\" type=\"doi\" claim=\"retraction\">10.5555/24242424x</crm-item>\r
                <crm-item name=\"relation\" type=\"doi\" claim=\"isReferencedBy\">10.5284/1000389</crm-item>\r
                <doi_record>\r
                  <crossref xmlns=\"http://www.crossref.org/xschema/1.1\" xsi:schemaLocation=\"http://www.crossref.org/xschema/1.1 http://doi.crossref.org/schemas/unixref1.1.xsd\">\r
                    <journal>\r
                      <journal_metadata language=\"en\">\r
                        <full_title>Journal of Psychoceramics</full_title>\r
                        <abbrev_title>Journal of Psychoceramics</abbrev_title>\r
                        <issn media_type=\"electronic\">0264-3561</issn>\r
                        <doi_data>\r
                          <doi>10.5555/1234567890</doi>\r
                          <resource>http://www.crossref.org</resource>\r
                        </doi_data>\r
                      </journal_metadata>\r
                      <journal_issue>\r
                        <publication_date media_type=\"online\">\r
                          <month>02</month>\r
                          <day>29</day>\r
                          <year>2008</year>\r
                        </publication_date>\r
                        <publication_date media_type=\"print\">\r
                          <month>02</month>\r
                          <day>29</day>\r
                          <year>2008</year>\r
                        </publication_date>\r
                        <journal_volume>\r
                          <volume>5</volume>\r
                        </journal_volume>\r
                        <issue>11</issue>\r
                      </journal_issue>\r
                      <journal_article language=\"en\">\r
                        <titles>\r
                          <title>Toward a Unified Theory of High-Energy Metaphysics: Silly String Theory</title>\r
                        </titles>\r
                        <contributors>\r
                          <person_name sequence=\"first\" contributor_role=\"author\">\r
                            <given_name>Josiah</given_name>\r
                            <surname>Carberry</surname>\r
                            <suffix>Jr.</suffix>\r
                            <affiliation>Department of Psychoceramics, Brown University</affiliation>\r
                            <ORCID>https://orcid.org/0000-0002-1825-0097</ORCID>\r
                          </person_name>\r
                          <organization sequence=\"additional\" contributor_role=\"chair\">Friends of Josiah Carberry</organization>\r
                        </contributors>\r
                        <jats:abstract xmlns:jats=\"http://www.ncbi.nlm.nih.gov/JATS1\">\r
                          <jats:p>The characteristic theme of the works of Stone is the bridge between culture and society. Several narratives concerning the fatal !aw, and subsequent dialectic, of semioticist class may be found. Thus, Debord uses the term ‘the subtextual paradigm of consensus’ to denote a cultural paradox. The subject is interpolated into a neocultural discourse that includes sexuality as a totality. But Marx’s critique of prepatriarchialist nihilism states that consciousness is capable of signi\"cance. The main theme of Dietrich’s[1]model of cultural discourse is not construction, but neoconstruction. Thus, any number of narratives concerning the textual paradigm of narrative exist. Pretextual cultural theory suggests that context must come from the collective unconscious.</jats:p>\r
                        </jats:abstract>\r
                        <publication_date media_type=\"online\">\r
                          <month>08</month>\r
                          <day>13</day>\r
                          <year>2008</year>\r
                        </publication_date>\r
                        <publication_date media_type=\"print\">\r
                          <month>08</month>\r
                          <day>14</day>\r
                          <year>2008</year>\r
                        </publication_date>\r
                        <pages>\r
                          <first_page>1</first_page>\r
                          <last_page>3</last_page>\r
                        </pages>\r
                        <publisher_item>\r
                          <item_number item_number_type=\"article_number\">1</item_number>\r
                        </publisher_item>\r
                        <crossmark>\r
                          <crossmark_policy>10.5555/something</crossmark_policy>\r
                          <crossmark_domains>\r
                            <crossmark_domain>\r
                              <domain>psychoceramics.labs.crossref.org</domain>\r
                            </crossmark_domain>\r
                          </crossmark_domains>\r
                          <updates>\r
                            <update type=\"corrigendum\" date=\"2018-01-01\">10.5555/12345678</update>\r
                          </updates>\r
                          <custom_metadata>\r
                            <assertion name=\"orcid\" label=\"ORCID\" explanation=\"IDs for Or\" href=\"http://orcid.org/0000-0002-1825-0097\" order=\"0\" />\r
                            <assertion name=\"received\" label=\"Received\" group_name=\"publication_history\" group_label=\"Publication History\" order=\"0\">2012-07-24</assertion>\r
                            <assertion name=\"accepted\" label=\"Accepted\" group_name=\"publication_history\" group_label=\"Publication History\" order=\"1\">2012-08-29</assertion>\r
                            <assertion name=\"published_online\" label=\"Published Online\" group_name=\"publication_history\" group_label=\"Publication History\" order=\"2\">2012-09-26</assertion>\r
                            <assertion name=\"published_print\" label=\"Published Print\" group_name=\"publication_history\" group_label=\"Publication History\" order=\"3\">2012-10-27</assertion>\r
                            <assertion name=\"peer_reviewed\" label=\"Peer reviewed\" group_name=\"peer_review\" group_label=\"Peer review\" explanation=\"thrice\" href=\"http://psychoceramics.labs.crossref.org/10.5555-12345678.html\" order=\"0\" />\r
                            <assertion name=\"supplementary_Material\" label=\"Supplementary Material\" explanation=\"Helpful Silly String resource\" href=\"http://www.silly-string.com/silly-info/index.cfm\" order=\"0\" />\r
                            <assertion group_name=\"copyright_licensing\" group_label=\"Copyright &amp; Licensing\" label=\"Licensing Information\" explanation=\"Complicated license information available\" href=\"http://http://psychoceramics.labs.crossref.org/10.5555-12345678.html\" order=\"0\" name=\"licensing\" />\r
                            <assertion group_name=\"copyright_licensing\" group_label=\"Copyright &amp; Licensing\" label=\"Copyright Statement\" explanation=\"Lorem Copyrightsum \" href=\"http://http://psychoceramics.labs.crossref.org/10.5555-12345678.html\" order=\"1\" name=\"copyright_statement\" />\r
                            <fr:program xmlns:fr=\"http://www.crossref.org/fundref.xsd\">\r
                              <fr:assertion name=\"fundgroup\">\r
                                <fr:assertion name=\"funder_name\">\r
                                  National Science Foundation\r
                                  <fr:assertion name=\"funder_identifier\">http://dx.doi.org/10.13039/100000001</fr:assertion>\r
                                </fr:assertion>\r
                                <fr:assertion name=\"award_number\">12345678</fr:assertion>\r
                              </fr:assertion>\r
                              <fr:assertion name=\"fundgroup\">\r
                                <fr:assertion name=\"funder_name\">\r
                                  Basic Energy Sciences, Office of Science, U.S. Department of Energy\r
                                  <fr:assertion name=\"funder_identifier\">http://dx.doi.org/10.13039/100006151</fr:assertion>\r
                                </fr:assertion>\r
                                <fr:assertion name=\"award_number\">12345679</fr:assertion>\r
                              </fr:assertion>\r
                            </fr:program>\r
                            <ai:program xmlns:ai=\"http://www.crossref.org/AccessIndicators.xsd\" name=\"AccessIndicators\">\r
                              <ai:license_ref applies_to=\"tdm\" start_date=\"2011-11-21\">http://psychoceramicsproprietrylicenseV1.com</ai:license_ref>\r
                              <ai:license_ref applies_to=\"vor\" start_date=\"2011-11-21\">http://psychoceramicsproprietrylicenseV1.com</ai:license_ref>\r
                              <ai:license_ref applies_to=\"am\" start_date=\"2011-11-21\">http://http://psychoceramicsproprietrylicenseV1.com</ai:license_ref>\r
                            </ai:program>\r
                            <program xmlns=\"http://www.crossref.org/clinicaltrials.xsd\">\r
                              <clinical-trial-number registry=\"10.18810/isrctn\">isrctn12345</clinical-trial-number>\r
                            </program>\r
                          </custom_metadata>\r
                        </crossmark>\r
                        <program xmlns=\"http://www.crossref.org/relations.xsd\" name=\"relations\">\r
                          <related_item>\r
                            <description>Retraction of 'Toward a Unified Theory of High-Energy Metaphysics: Silly String Theory'</description>\r
                            <inter_work_relation relationship-type=\"isReplyTo\" identifier-type=\"doi\">10.5555/24242424x</inter_work_relation>\r
                          </related_item>\r
                        </program>\r
                        <archive_locations>\r
                          <archive name=\"CLOCKSS\" />\r
                        </archive_locations>\r
                        <doi_data>\r
                          <doi>10.5555/12345678</doi>\r
                          <resource>http://psychoceramics.labs.crossref.org/10.5555-12345678.html</resource>\r
                          <collection property=\"crawler-based\">\r
                            <item crawler=\"iParadigms\">\r
                              <resource>http://psychoceramics.labs.crossref.org/10.5555-12345678.html</resource>\r
                            </item>\r
                          </collection>\r
                        </doi_data>\r
                        <citation_list>\r
                          <citation key=\"ref0\">\r
                            <journal_title>Eur Resp J</journal_title>\r
                            <author>Boucher</author>\r
                            <volume>23</volume>\r
                            <first_page>146</first_page>\r
                            <cYear>2004</cYear>\r
                            <doi>10.1183/09031936.03.00057003</doi>\r
                            <article_title>New concepts of the pathogenesis of cystic fibrosis lung disease</article_title>\r
                            <unstructured_citation>1. Boucher RC (2004) New concepts of the pathogenesis of cystic fibrosis lung disease. Eur Resp J 23: 146–158.</unstructured_citation>\r
                          </citation>\r
                          <citation key=\"ref1\">\r
                            <journal_title>J Clin Investig</journal_title>\r
                            <author>MR</author>\r
                            <volume>109</volume>\r
                            <first_page>571</first_page>\r
                            <cYear>2002</cYear>\r
                            <doi>10.1172/JCI0215217</doi>\r
                            <article_title>Mucus clearance as a primary innate defense mechanism for mammalian airways</article_title>\r
                            <unstructured_citation>2. Knowles MR, Boucher RC (2002) Mucus clearance as a primary innate defense mechanism for mammalian airways. J Clin Investig 109: 571–577.</unstructured_citation>\r
                          </citation>\r
                          <citation key=\"ref2\">\r
                            <journal_title>Curr Opin Allergy Clin Immunol</journal_title>\r
                            <author>MB</author>\r
                            <volume>7</volume>\r
                            <first_page>5</first_page>\r
                            <cYear>2007</cYear>\r
                            <doi>10.1097/ACI.0b013e3280114eef</doi>\r
                            <article_title>Mucociliary clearance - a critical upper airway host defense mechanism and methods of assessment</article_title>\r
                            <unstructured_citation>3. Antunes MB, Cohen NA (2007) Mucociliary clearance - a critical upper airway host defense mechanism and methods of assessment. Curr Opin Allergy Clin Immunol 7: 5–10.</unstructured_citation>\r
                          </citation>\r
                          <citation key=\"ref3\">\r
                            <doi provider=\"crossref\">10.1126/science.2475911</doi>\r
                            <unstructured_citation>1. Riordan JR, Rommens JM, Kerem BS, Alon N, Rozmahel R, et al. (1989) Identification of the Cystic-Fibrosis Gene - Cloning and Characterization of Com</unstructured_citation>\r
                          </citation>\r
                        </citation_list>\r
                      </journal_article>\r
                    </journal>\r
                  </crossref>\r
                </doi_record>\r
              </query>\r
            </body>\r
          </query_result>\r
        </crossref_result>")

(deftest article-from-doi-test
  (testing "should return nil if doi does not exist"
    (with-redefs (client/get (fn [_] {:status 404}))
      (is (= nil (article-from-doi "doi-does-not-exist")))))
  (testing "should return article info map if doi exists"
    (with-redefs (client/get (fn [_] {:body xml-from-crossref}))
      (is (= {:DOI                    "doi-exists"
              :is-referenced-by-count "2"
              :publisher              "Society of Psychoceramics"
              :type                   :journal-article
              :member                 "17333"}
             (article-from-doi "doi-exists"))))))

(deftest parse-xml-test
  (testing "should convert xml to clojure map"
    (is (= [{:tag     :article
             :attrs   nil
             :content ["Hello world"]}
            nil]
           (parse-xml "<article>Hello world</article>")))))

(deftest find-article-info-test
  (testing "should find query node in xml map"
    (let [xml-map [{:tag     :crossref_result,
                    :attrs   {},
                    :content [{:tag     :query_result,
                               :attrs   nil,
                               :content [{:tag :head, :attrs nil, :content [{:tag :doi_batch_id, :attrs nil, :content ["none"]}]}
                                         {:tag     :body,
                                          :attrs   nil,
                                          :content [{:tag     :query,
                                                     :attrs   {:status "resolved"},
                                                     :content ["Query tag content"]}]}]}]}]]
      (is (= ["Query tag content"] (query-tag xml-map)))))
  (testing "should get crm item from query list by :name"
    (let [query-list [{:tag     :doi,
                       :attrs   {:type "journal_article"},
                       :content ["10.5555/12345678"]}
                      {:tag     :crm-item,
                       :attrs   {:type "number", :name "citedby-count"},
                       :content ["2"]}
                      {:tag     :crm-item,
                       :attrs   {:type "string", :name "prefix-name"},
                       :content ["Society of Psychoceramics"]}]]
      (is (= "2" (crm-content-from query-list "citedby-count")))))
  (testing "should turn query list into article info map"
    (let [doi "10.5555/12345678"
          query-list [{:tag     :doi,
                       :attrs   {:type "journal_article"},
                       :content [doi]}
                      {:tag     :crm-item,
                       :attrs   {:type "string", :name "publisher-name"},
                       :content ["Society of Psychoceramics"]}
                      {:tag     :crm-item,
                       :attrs   {:type "number", :name "member-id"}
                       :content ["17333"]}
                      {:tag     :crm-item,
                       :attrs   {:type "number", :name "citedby-count"},
                       :content ["2"]}
                      {:tag     :crm-item,
                       :attrs   {:type "number", :name "member-id"},
                       :content ["17333"]}]]
      (is (= {:DOI                    "10.5555/12345678"
              :is-referenced-by-count "2"
              :publisher              "Society of Psychoceramics"
              :type                   :journal-article
              ;:title                  ["Toward a Unified Theory of High-Energy Metaphysics: Silly String Theory"]
              :member                 "17333"
              }
             (article-info-map query-list doi))))))