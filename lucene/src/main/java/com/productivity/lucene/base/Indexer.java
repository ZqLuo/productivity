package com.productivity.lucene.base;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Map;

public class Indexer {

    private IndexWriter writer;

    public Indexer(String indexDirPath) throws IOException{
        Analyzer analyzer = new StandardAnalyzer();
        Directory directory = FSDirectory.open(Paths.get(indexDirPath));
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter iwriter = new IndexWriter(directory, config);
    }
    public void close() throws IOException{
        writer.close();
    }

    /**
     * 获取文件文档
     * @param file
     * @return
     * @throws IOException
     */
    private Document getFileDocument(File file) throws IOException{
        Document document = new Document();
        //index file contents
        Field contentField = new TextField(LuceneConstants.CONTENTS, new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)));
        //index file name
        Field fileNameField = new StringField(LuceneConstants.FILE_NAME,file.getName(), Field.Store.YES);
        //index file path
        Field filePathField = new StringField(LuceneConstants.FILE_PATH,file.getCanonicalPath(), Field.Store.YES);
        document.add(contentField);
        document.add(fileNameField);
        document.add(filePathField);
        return document;
    }

    /**
     * 文件建立索引
     * @param file
     * @throws IOException
     */
    private void indexFile(File file) throws IOException{
        System.out.println("Indexing "+file.getCanonicalPath());
        Document document = getFileDocument(file);
        writer.addDocument(document);
    }

    public int createFileIndex(String dataDirPath, FileFilter filter)
            throws IOException {
        //get all files in the data directory
        File[] files = new File(dataDirPath).listFiles();

        for (File file : files) {
            if(!file.isDirectory()
                    && !file.isHidden()
                    && file.exists()
                    && file.canRead()
                    && filter.accept(file)
            ){
                indexFile(file);
            }
        }
        return writer.numDocs();
    }

    public int createMapIndex(){

        return writer.numDocs();
    }
}
