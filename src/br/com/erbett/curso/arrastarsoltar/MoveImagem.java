/* 
 * Copyright 2016 Erbett H. R. Oliveira, Inc. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 * 		
 * 		1. Redistributions of source code must retain the above copyright notice, this list of
 * 			conditions and the following disclaimer.
 * 
 * 		2. Redistributions in binary form must reproduce the above copyright notice, this list
 * 		   of conditions and the following disclaimer in the documentation and/or other materials
 *         provided with the distribution.
 *         
 * THIS SOFTWARE IS PROVIDED BY ERBETT HINTON RIBEIRO OLIVEIRA, INC. ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, AND LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL ERBETT HINTON RIBEIRO OLIVEIRA, INC. OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package br.com.erbett.curso.arrastarsoltar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/*
 * Codigo fonte copiado do Livro Google Android Edicao 3
 * 
 *
 */
public class MoveImagem extends View {
	
	private int widthImagem;
	private int heightImagem;

	private int x;
	private int y;
	
	private boolean selecionado;
	
	private Drawable drawable;
	
	public MoveImagem(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		drawable = context.getResources().getDrawable(R.drawable.ic_launcher);
		
		widthImagem 	= drawable.getIntrinsicWidth();
		heightImagem 	= drawable.getIntrinsicHeight();
		
	}
	
	@Override
	protected void onSizeChanged(final int width, final int height, final int oldw, final int oldh) {
		super.onSizeChanged(width,height,oldw,oldh);
		
		x = width  / 2 - (widthImagem  / 2);
		y = height / 2 - (heightImagem / 2);
		

	}

	@Override
	protected void onDraw(final Canvas canvas) {
		super.onDraw(canvas);
		
		if (drawable != null && isEnabled()){
			drawable.setBounds(x, y, x + widthImagem, y + heightImagem);
			drawable.draw(canvas);
		}
	}
	
	public boolean onTouchEvent(final MotionEvent event) {

		float x = event.getX();
		float y = event.getY();
				
		switch (event.getAction()) {
		
		case MotionEvent.ACTION_DOWN:
			selecionado = drawable.copyBounds().contains((int)x,(int) y);
		break;
		case (MotionEvent.ACTION_MOVE):
			if (selecionado){
				this.x = (int) x - (widthImagem  / 2);
				this.y = (int) y - (heightImagem / 2);
				
				
			}
			break;
			
		case MotionEvent.ACTION_UP:
			selecionado = false;
			break;
				
		default:
			break;
		}
		
		invalidate();
		
		return true;
	}
}
