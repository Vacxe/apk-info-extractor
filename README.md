# Apk info extractor
CLI tool for extracting info from apk

### How to install
#### Brew

```
brew tap vacxe/tap
brew install vacxe/tap/apkinfoextractor
```

#### Docker
*Image contains JQ*

```
docker pull vacxe/apk-info-extractor
```

### Usage

```
apkinfoextractor <path to apk>
```

Result sample

```
{"package":"your.package.name","versionCode":5,"versionName":"1.0.1"}
```

Can be easily combined with JQ library
