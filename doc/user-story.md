# Create an app to convert the xml of an article to json


### User Story

**As a** member of CrossRef 

**I want** to retrieve information about an article in JSON format from the DOI

**So that** I don't have to deal with XML


### Background

Some of our members want to be able to be able to get the reference-count, publisher name,
type, titles and member id for a given DOI. They can't work with XML, so they want the data as
a JSON REST API.

* Each content item is represented by a DOI which looks like: "10.5555/12345678". 

* To find some random journal-article DOIs you can visit 
  https://api.crossref.org/v1/works?filter=type:journal-article&sample=10

* A DOI is made up of a prefix, which is "10." followed by a string of numbers, followed by
  a slash, followed by a suffix which is a sequence of any printable unicode characters.

* To retrieve the XML for a given DOI you can visit
  https://api.crossref.org/v1/works/THE_DOI/transform/application/vnd.crossref.unixsd+xml

  e.g. http://api.crossref.org/v1/works/10.5555/12345678/transform/application/vnd.crossref.unixsd+xml
  
* The JSON should have only the following fields:
  - DOI
  - is-referenced-by-count (this is called "citedby-count" in the XML)
  - publisher (this is called "publisher-name" in the XML)
  - title (list)
  - member (this is "member-id" in the XML)


### Assumptions

* If the DOI is valid and exists then all required fields exist for that article
  
  
### Acceptance Criteria

#### 1.  A valid DOI that exists

**Given** a valid DOI that exists, 10.5555/12345678

**When** you perform a GET request to the endpoint /works/10.5555/12345678

**Then** you receive a 200 OK response code

**And** the contents of the response would be 

    {
        "DOI": "10.5555/12345678", 
        "is-referenced-by-count": 2,
        "publisher": "Society of Psychoceramics",
        "type": "journal-article",
        "title": [ "Toward a Unified Theory of High-Energy Metaphysics: Silly String Theory" ],
        "member": "17333" 
    }
    
#### 2. A valid DOI that does not exist

**Given** a valid DOI that does not exist, 10.5555/11111111

**When** you perform a GET request to the endpoint /works/10.5555/11111111

**Then** you receive a 404 NOT FOUND response

#### 3. An invalid DOI

**Given** an invalid DOI, not-a-doi

**When** you perform a GET request to the endpoint /works/not-a-doi

**Then** you receive a 400 BAD REQUEST response

#### 4. An invalid URL

**Given** an invalid endpoint, /not-an-endpoint

**When** you perform a GET request to the endpoint /not-an-endpoint

**Then** you receive a 404 NOT FOUND response


## Technical Details

* Should package into a Docker image suitable to run in a container service.
* Should run on a configurable port.