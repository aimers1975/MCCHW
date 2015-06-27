#include "stdafx.h"
#include "omp.h"
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
using namespace std;

/*bool MatrixMult(int rowA, int colA, double* A, int rowB, int colB, double* B,
	double* C, int T) {
	// TODO: Implement your parallel multiplication for two matrices of doubles
	// by using OpenMP
	// parameter rowA: indicates the number of rows of the matrix A
	// parameter colA: indicates the number of columns of the matrix A
	// parameter A: indicates the matrix A
	// parameter rowB: indicates the number of rows of the matrix B
	// parameter colB: indicates the number of columns of the matrix B
	// parameter B: indicates the matrix B
	// parameter C: indicates the matrix C, which is the results of A x B
	// parameter T: indicates the number of threads
	// return true if A and B can be multiplied; otherwise, return false 
	if (ROWA != COLB) {
	cout << "The matix cannot be mult.";
	cin.get();
	return false;
	}
	else {
	cout << "The matix can be mult.";
	cin.get();
	}
}*/


int main(int argc, const char *argv[]) {
	int ROWA;
	int COLA;
	int ROWB;
	int COLB;
	int T;

	if (argc < 3) {
		cerr << "Please enter files and number of threads!" << endl;
		cerr << "Example: ./multiply matrix1.txt matrix2.txt 5" << endl;
		cin.get();
		exit(1);
	}

	ifstream inf(argv[1]);
	cout << "The first file is: " << argv[1] << "\n";
	ifstream inf2(argv[2]);
	cout << "The second file is: " << argv[2] << "\n";
	cout << "The threads are: " << argv[3] << "\n";
	stringstream str; 
	str << argv[3];
	str >> T;


	// If we couldn't open the output file stream for reading
	if (!inf || !inf2)
	{	// Print an error and exit
		cerr << "Uh oh, one of the files could not be opened for reading!" << endl;
		cin.get();
		exit(1);
	}
	// Read in rows and columns for the matrix.
	inf >> ROWA >> COLA;
	inf2 >> ROWB >> COLB;
	double** A;
	double** B;

	A = new double*[ROWA];
	for (int i = 0; i < ROWA; i++) {
		A[i] = new double[COLA];
		for (int j = 0; j < COLA; j++) {
			inf >> A[i][j];
			cout << "Row: " << i << " col: " << j << " is read: " << A[i][j] << "\n";
		}
	}

	B = new double*[ROWB];
	for (int i = 0; i < ROWB; i++) {
		B[i] = new double[COLB];
		for (int j = 0; j < COLB; j++) {
			inf2 >> B[i][j];
			cout << "Row: " << i << " col: " << j << " is read: " << B[i][j] << "\n";
		}
	}

	/*int aMatrix[3][2] = {{1, 4}, {2, 5}, {3, 6}};
    int bMatrix[2][3] = {{7, 8, 9}, {10, 11, 12}};
    int product[3][3] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 3; col++) {
            // Multiply the row of A by the column of B to get the row, column of product.
            for (int inner = 0; inner < 2; inner++) {
                product[row][col] += aMatrix[row][inner] * bMatrix[inner][col];
            }
            std::cout << product[row][col] << "  ";
        }
        std::cout << "\n";
    }
}	*/



	// TODO: Initialize the necessary data

	/*if (MatrixMult(ROWA, COLA, A, ROWB, COLB, B, C, T)) {
		// TODO: Output the results
	}
	else {
		cout << "the colA != rowB MatrixMult return false" << endl;
	}
	return 0;*/
	if (ROWA != COLB) {
		cout << "The matix cannot be mult.";
		cout << "ROWA: " << ROWA << " and COLB is: " << COLB << "\n";
		cin.get();
		return false;
	}
	else {
		cout << "The matix can be mult.";
		cout << "ROWA: " << ROWA << " and COLB is: " << COLB << "\n";
		cin.get();
	}
	return 0;
}
