# doi-xml-converter

An app that converts application data from xml to json using the DOI

## Installation

Requires:
    
- Clojure 1.10.0
- Java 8+
- Leiningen https://leiningen.org/#install

Clone the code from https://github.com/bonnieappleton/doi-xml-converter

## Usage

To run the app:

    $ lein run
    
To run the tests:

    $ lein test
    
#### Using Docker

    $ docker build -t doi-xml-converter .
    $ docker run -d -p 8000:8000 doi-xml-converter

## The API

The endpoint `/works/{DOI}` returns JSON with the following fields for the given DOI

  - DOI
  - is-referenced-by-count
  - publisher
  - type
  - title
  - member

#### Example

Go to `http://localhost:8000/works/10.2307/1320590` to see the JSON output once the app is running on your machine

    {
        "DOI":"10.2307/1320590",
        "is-referenced-by-count":"0",
        "publisher":"Informa UK Limited",
        "type":"journal-article",
        "title":["An Artist of the American Renaissance: The Letters of Kenyon Cox, 1883-1919"],
        "member":"301"
    }